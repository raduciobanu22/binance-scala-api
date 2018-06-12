package com.binance.api.examples

import java.util

import com.binance.api.client.domain.event.DepthEvent
import com.binance.api.client.domain.market.OrderBook
import com.binance.api.client.domain.{Symbol, OrderBookEntry, Price, Quantity}
import com.binance.api.client.impl.BinanceApiWebSocketClientImpl
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Illustrates how to use the depth event stream to create a local cache of bids/asks for a symbol.
  */
object DepthCacheExample extends App {
  class Cache(var lastUpdateId: Long,
              var bids:         util.NavigableMap[Price, Quantity],
              var asks:         util.NavigableMap[Price, Quantity])

  val PriceOrdering: Ordering[Price] =
    Ordering[BigDecimal].on[Price](_.value).reverse

  /**
    * Initializes the depth cache by using the REST API.
    */
  private def initializeDepthCache(client: BinanceApiAsyncRestClient, symbol: Symbol) =
    client
      .getOrderBook(symbol, 10)
      .map { (orderBook: OrderBook) =>
        val asks = new util.TreeMap[Price, Quantity](PriceOrdering)
        for (ask <- orderBook.asks) {
          asks.put(ask.price, ask.qty)
        }
        val bids = new util.TreeMap[Price, Quantity](PriceOrdering)
        for (bid <- orderBook.bids) {
          bids.put(bid.price, bid.qty)
        }
        new Cache(orderBook.lastUpdateId, bids, asks)
      }

  /**
    * Begins streaming of depth events.
    */
  private def startDepthEventStreaming(client: BinanceApiWebSocketClientImpl, cache: Cache, symbol: Symbol) =
    client.onDepthEvent(symbol)((response: DepthEvent) => {
      if (response.firstUpdateId > cache.lastUpdateId) {
        println(response)
        cache.lastUpdateId = response.lastUpdateId
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
  private def updateOrderBook(lastOrderBookEntries: util.NavigableMap[Price, Quantity], entries: List[OrderBookEntry]) =
    for (entry <- entries) {
      if (entry.qty.value == BigDecimal(0)) { // qty=0 means remove this level
        lastOrderBookEntries.remove(entry.price)
      } else lastOrderBookEntries.put(entry.price, entry.qty)
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
  private def printDepthCache(cache: Cache): Unit = {

    /**
      * Pretty prints an order book entry in the format "price / quantity".
      */
    def format(entry: util.Map.Entry[Price, Quantity]) =
      entry.getKey.value.toString() + " / " + entry.getValue.value

    println("ASKS:")
    cache.asks.entrySet.forEach((entry) => println(format(entry)))
    println("BIDS:")
    cache.bids.entrySet.forEach((entry) => println(format(entry)))
    println("BEST ASK: " + format(getBestAsk(cache)))
    println("BEST BID: " + format(getBestBid(cache)))
  }

  val symbol  = Symbol("ETHBTC")
  val factory = new BinanceApiClientFactory("", "")
  val client  = factory.newAsyncRestClient

  initializeDepthCache(client, symbol)
    .map(cache => startDepthEventStreaming(factory.newWebSocketClient, cache, symbol))
    .failed
    .foreach(println)

}
