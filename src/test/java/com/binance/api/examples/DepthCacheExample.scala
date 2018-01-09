package com.binance.api.examples

import java.math.BigDecimal
import java.util

import com.binance.api.client.domain.OrderBookEntry
import com.binance.api.client.domain.event.DepthEvent
import com.binance.api.client.domain.market.OrderBook
import com.binance.api.client.impl.BinanceApiWebSocketClientImpl
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Illustrates how to use the depth event stream to create a local cache of bids/asks for a symbol.
  */
object DepthCacheExample extends App {
  class Cache(var lastUpdateId: Long,
              var bids:         util.NavigableMap[BigDecimal, BigDecimal],
              var asks:         util.NavigableMap[BigDecimal, BigDecimal])

  /**
    * Initializes the depth cache by using the REST API.
    */
  private def initializeDepthCache(client: BinanceApiAsyncRestClient, symbol: String) =
    client
      .getOrderBook(symbol.toUpperCase, 10)
      .map { (orderBook: OrderBook) =>
        val asks = new util.TreeMap[BigDecimal, BigDecimal](util.Comparator.reverseOrder[BigDecimal])
        for (ask <- orderBook.asks) {
          asks.put(new BigDecimal(ask.price), new BigDecimal(ask.qty))
        }
        val bids = new util.TreeMap[BigDecimal, BigDecimal](util.Comparator.reverseOrder[BigDecimal])
        for (bid <- orderBook.bids) {
          bids.put(new BigDecimal(bid.price), new BigDecimal(bid.qty))
        }
        new Cache(orderBook.lastUpdateId, bids, asks)
      }

  /**
    * Begins streaming of depth events.
    */
  private def startDepthEventStreaming(client: BinanceApiWebSocketClientImpl, cache: Cache, symbol: String) =
    client.onDepthEvent(symbol.toLowerCase)((response: DepthEvent) => {

      if (response.updateId > cache.lastUpdateId) {
        println(response)
        cache.lastUpdateId = response.updateId
        updateOrderBook(cache.asks, response.asks)
        updateOrderBook(cache.bids, response.bids)
        printDepthCache(cache)
      }
    })

  /**
    * Updates an order book (bids or asks) with a delta received from the server.
    * <p>
    * Whenever the qty specified is ZERO, it means the price should was removed from the order book.
    */
  private def updateOrderBook(lastOrderBookEntries: util.NavigableMap[BigDecimal, BigDecimal],
                              orderBookDeltas:      List[OrderBookEntry]) =
    for (orderBookDelta <- orderBookDeltas) {
      val price = new BigDecimal(orderBookDelta.price)
      val qty   = new BigDecimal(orderBookDelta.qty)
      if (qty.compareTo(BigDecimal.ZERO) == 0) { // qty=0 means remove this level
        lastOrderBookEntries.remove(price)
      } else lastOrderBookEntries.put(price, qty)
    }

  /**
    * @return the best ask in the order book
    */
  private def getBestAsk(cache: Cache) = cache.asks.lastEntry

  /**
    * @return the best bid in the order book
    */
  private def getBestBid(cache: Cache) = cache.bids.firstEntry

  /**
    * Prints the cached order book / depth of a symbol as well as the best ask and bid price in the book.
    */
  private def printDepthCache(cache: Cache) = {

    /**
      * Pretty prints an order book entry in the format "price / quantity".
      */
    def format(entry: util.Map.Entry[BigDecimal, BigDecimal]) =
      entry.getKey.toPlainString + " / " + entry.getValue

    println("ASKS:")
    cache.asks.entrySet.forEach((entry) => println(format(entry)))
    println("BIDS:")
    cache.bids.entrySet.forEach((entry) => println(format(entry)))
    println("BEST ASK: " + format(getBestAsk(cache)))
    println("BEST BID: " + format(getBestBid(cache)))
  }

  val symbol = "ETHBTC"
  val factory: BinanceApiClientFactory   = new BinanceApiClientFactory("", "")
  val client:  BinanceApiAsyncRestClient = factory.newAsyncRestClient

  initializeDepthCache(client, symbol).map(
    cache => startDepthEventStreaming(factory.newWebSocketClient, cache, symbol)
  ).failed.foreach(println)

}
