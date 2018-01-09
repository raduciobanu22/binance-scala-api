package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.event.{AggTradeEvent, CandlestickEvent, DepthEvent}
import com.binance.api.client.domain.market.CandlestickInterval
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Market data stream endpoints examples.
  *
  * It illustrates how to create a stream to obtain updates on market data such as executed trades.
  */
object MarketDataStreamExample extends App {

  val client = new BinanceApiClientFactory("", "").newWebSocketClient

  // Listen for aggregated trade events for ETH/BTC
  client.onAggTradeEvent("ethbtc")((response: AggTradeEvent) => println(response))
  // Listen for changes in the order book in ETH/BTC
  client.onDepthEvent("ethbtc")((response: DepthEvent) => println(response))
  // Obtain 1m candlesticks in real-time for ETH/BTC
  client.onCandlestickEvent("ethbtc", CandlestickInterval.ONE_MINUTE)((response: CandlestickEvent) => println(response))
}