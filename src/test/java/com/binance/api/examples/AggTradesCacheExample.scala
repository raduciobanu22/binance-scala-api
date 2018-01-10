package com.binance.api.examples

import com.binance.api.client.domain.{AggTradeBase, Symbol}
import com.binance.api.client.domain.event.AggTradeEvent
import com.binance.api.client.domain.market.AggTrade
import com.binance.api.client.impl.{BinanceApiAsyncRestClientImpl, BinanceApiWebSocketClientImpl}
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Illustrates how to use the aggTrades event stream to create a local cache of trades for a symbol.
  */
object AggTradesCacheExample extends App {
  type Cache = mutable.Map[Long, AggTradeBase]

  val factory         = new BinanceApiClientFactory("", "")
  val restClient      = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient
  val symbol          = Symbol("ETHBTC")

  /**
    * Initializes the aggTrades cache by using the REST API.
    *
    * @return Key is the aggregate trade id, and the value contains the aggregated trade data, which is
    *         automatically updated whenever a new agg data stream event arrives.
    */
  def initialCache(client: BinanceApiAsyncRestClient, symbol: Symbol): Future[Cache] =
    client
      .getAggTrades(symbol)
      .map(trades => mutable.Map.empty ++ trades.map(trade => trade.aggregatedTradeId -> trade))

  /**
    * Begins streaming of agg trades events.
    */
  def startAggTradesEventStreaming(webSocketClient: BinanceApiWebSocketClient,
                                   aggTradesCache:  Cache,
                                   symbol:          Symbol): Unit =
    webSocketClient.onAggTradeEvent(symbol)((response: AggTradeEvent) => {
      aggTradesCache.put(response.aggregatedTradeId, response)
      System.out.println(response)
    })

  for {
    cache <- initialCache(restClient, symbol)
  } yield startAggTradesEventStreaming(webSocketClient, cache, symbol)

}
