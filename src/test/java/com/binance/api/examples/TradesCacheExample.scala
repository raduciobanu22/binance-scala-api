package com.binance.api.examples

import com.binance.api.client.domain.event.TradeEvent
import com.binance.api.client.domain.{TradeBase, Symbol}
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Illustrates how to use the trades event stream to create a local cache of trades for a symbol.
  */
object TradesCacheExample extends App {
  type Cache = mutable.Map[Long, TradeBase]

  val factory         = new BinanceApiClientFactory("", "")
  val restClient      = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient
  val symbol          = Symbol("ETHBTC")

  /**
    * Initializes the trades cache by using the REST API.
    *
    * @return Key is the trade id, and the value contains the trade data, which is
    *         automatically updated whenever a new data stream event arrives.
    */
  def initialCache(client: BinanceApiAsyncRestClient, symbol: Symbol): Future[Cache] =
    client
      .getTrades(symbol)
      .map(trades => mutable.Map.empty ++ trades.map(trade => trade.tradeId -> trade))

  /**
    * Begins streaming of trade events.
    */
  def startTradesEventStreaming(webSocketClient: BinanceApiWebSocketClient,
                                tradesCache:     Cache,
                                symbol:          Symbol): Unit =
    webSocketClient.onTradeEvent(symbol)((response: TradeEvent) => {
      tradesCache.put(response.tradeId, response)
      System.out.println(response)
    })

  for {
    cache <- initialCache(restClient, symbol)
  } yield startTradesEventStreaming(webSocketClient, cache, symbol)

}
