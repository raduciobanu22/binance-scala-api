package com.binance.api.examples

import java.util

import com.binance.api.client.domain.{CandlestickBase, Symbol, Instant}
import com.binance.api.client.domain.event.CandlestickEvent
import com.binance.api.client.domain.market.CandlestickInterval
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Illustrates how to use the klines/candlesticks event stream to create a local cache of bids/asks for a symbol.
  */
object CandlesticksCacheExample extends App {
  type Cache = util.TreeMap[Instant, CandlestickBase]

  val symbol          = Symbol("ETHBTC")
  val interval        = CandlestickInterval.ONE_MINUTE
  val factory         = new BinanceApiClientFactory("", "")
  val client          = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient

  /**
    * Initializes the candlestick cache by using the REST API.
    */
  def initializeCandlestickCache(client:   BinanceApiAsyncRestClient,
                                 symbol:   Symbol,
                                 interval: CandlestickInterval): Future[Cache] =
    client.getCandlestickBars(symbol, interval).map { candlestickBars =>
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
                                     symbol:            Symbol,
                                     interval:          CandlestickInterval) =
    client.onCandlestickEvent(symbol, interval) { (response: CandlestickEvent) =>
      candlesticksCache.put(response.candlestick.openTime, response.candlestick)
      System.out.println(response)
    }

  initializeCandlestickCache(client, symbol, interval).foreach(
    cache => startCandlestickEventStreaming(webSocketClient, cache, symbol, interval)
  )
}
