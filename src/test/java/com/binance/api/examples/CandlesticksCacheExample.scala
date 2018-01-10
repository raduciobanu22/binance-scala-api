package com.binance.api.examples

import java.util

import com.binance.api.client.domain.CandlestickBase
import com.binance.api.client.domain.event.CandlestickEvent
import com.binance.api.client.domain.market.CandlestickInterval
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Illustrates how to use the klines/candlesticks event stream to create a local cache of bids/asks for a symbol.
  */
object CandlesticksCacheExample extends App {
  type Cache = util.TreeMap[Long, CandlestickBase]

  val symbol          = "ETHBTC"
  val interval        = CandlestickInterval.ONE_MINUTE
  val factory         = new BinanceApiClientFactory("", "")
  val client          = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient

  /**
    * Initializes the candlestick cache by using the REST API.
    */
  def initializeCandlestickCache(client:   BinanceApiAsyncRestClient,
                                 symbol:   String,
                                 interval: CandlestickInterval): Future[Cache] =
    client.getCandlestickBars(symbol.toUpperCase, interval).map { candlestickBars =>
      val candlesticksCache = new Cache
      for (candlestickBar <- candlestickBars) {
        candlesticksCache.put(candlestickBar.openTime, candlestickBar)
      }
      candlesticksCache
    }

  /**
    * Begins streaming of depth events.
    */
  def startCandlestickEventStreaming(client:            BinanceApiWebSocketClient,
                                     candlesticksCache: Cache,
                                     symbol:            String,
                                     interval:          CandlestickInterval) =
    client.onCandlestickEvent(symbol.toLowerCase, interval) { (response: CandlestickEvent) =>
      candlesticksCache.put(response.candlestick.openTime, response.candlestick)
      System.out.println(response)
    }

  initializeCandlestickCache(client, symbol, interval).foreach(
    cache => startCandlestickEventStreaming(webSocketClient, cache, symbol, interval)
  )
}
