package com.binance.api.examples

import com.binance.api.client.domain.AggTradeBase
import com.binance.api.client.domain.event.AggTradeEvent
import com.binance.api.client.domain.market.AggTrade
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Illustrates how to use the aggTrades event stream to create a local cache of trades for a symbol.
  */
object AggTradesCacheExample extends App {
  val factory = new BinanceApiClientFactory("", "")
  new AggTradesCacheExample(factory.newAsyncRestClient, factory.newWebSocketClient, "ETHBTC")
}

class AggTradesCacheExample(val restClient:      BinanceApiAsyncRestClient,
                            val webSocketClient: BinanceApiWebSocketClient,
                            val symbol:          String) {
  for {
    cache <- initialCache(restClient, symbol)
  } yield startAggTradesEventStreaming(webSocketClient, cache, symbol)

  /**
    * Initializes the aggTrades cache by using the REST API.
    *
    * @return Key is the aggregate trade id, and the value contains the aggregated trade data, which is
    *         automatically updated whenever a new agg data stream event arrives.
    */
  private def initialCache(client: BinanceApiAsyncRestClient, symbol: String): Future[mutable.Map[Long, AggTradeBase]] =
    client
      .getAggTrades(symbol.toUpperCase)
      .map(trades => mutable.Map.empty[Long, AggTrade] ++ trades.map(trade => trade.aggregatedTradeId -> trade))

  /**
    * Begins streaming of agg trades events.
    */
  private def startAggTradesEventStreaming(webSocketClient: BinanceApiWebSocketClient,
                                           aggTradesCache:  mutable.Map[Long, AggTradeBase],
                                           symbol:          String): Unit =
    webSocketClient.onAggTradeEvent(symbol.toLowerCase)((response: AggTradeEvent) => {
      aggTradesCache.put(response.aggregatedTradeId, response)
      System.out.println(response)
    })
}
