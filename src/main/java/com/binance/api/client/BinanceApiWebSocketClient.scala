package com.binance.api.client

import com.binance.api.client.domain.event._
import com.binance.api.client.domain.market.CandlestickInterval

/**
  * Binance API data streaming façade, supporting streaming of events through web sockets.
  */
trait BinanceApiWebSocketClient {
  def onDepthEvent(symbol: String)(callback: DepthEvent => Unit): Unit

  def onCandlestickEvent(symbol: String, interval: CandlestickInterval)(
      callback:                  CandlestickEvent => Unit
  ): Unit

  def onAggTradeEvent(symbol: String)(callback: AggTradeEvent => Unit): Unit

  def onUserDataUpdateEvent(listenKey: ListenKey)(
      callback:                        Either[OrderTradeUpdateEvent, AccountUpdateEvent] => Unit
  ): Unit
}
