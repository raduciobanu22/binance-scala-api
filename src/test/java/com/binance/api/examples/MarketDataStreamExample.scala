package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.Symbol
import com.binance.api.client.domain.event.{AggTradeEvent, CandlestickEvent, DepthEvent, TradeEvent}
import com.binance.api.client.domain.market.CandlestickInterval

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Market data stream endpoints examples.
  *
  * It illustrates how to create a stream to obtain updates on market data such as executed trades.
  */
object MarketDataStreamExample extends App {
  val webSocket = new BinanceApiClientFactory("", "").newWebSocketClient
  val symbol    = Symbol("ethbtc")

  // Listen for aggregated trade events for ETH/BTC
  webSocket.onAggTradeEvent(symbol)((response: AggTradeEvent) => println(response))

  // Listen for trade events for ETH/BTC
  webSocket.onTradeEvent(symbol)((response: TradeEvent) => println(response))

  // Listen for changes in the order book in ETH/BTC
  webSocket.onDepthEvent(symbol)((response: DepthEvent) => println(response))

  // Obtain 1m candlesticks in real-time for ETH/BTC
  webSocket.onCandlestickEvent(symbol, CandlestickInterval.ONE_MINUTE)(
    (response: CandlestickEvent) => println(response)
  )
}
