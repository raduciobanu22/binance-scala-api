package com.binance.api.client

import com.binance.api.client.domain.Symbol
import com.binance.api.client.domain.event._
import com.binance.api.client.domain.market.CandlestickInterval

/**
  * Binance API data streaming façade, supporting streaming of events through web sockets.
  */
trait BinanceApiWebSocketClient {
  def onDepthEvent(symbol: Symbol)(callback: DepthEvent => Unit): Unit

  def onCandlestickEvent(symbol: Symbol, interval: CandlestickInterval)(
      callback:                  CandlestickEvent => Unit
  ): Unit

  def onAggTradeEvent(symbol: Symbol)(callback: AggTradeEvent => Unit): Unit

  def onTradeEvent(symbol: Symbol)(callback: TradeEvent => Unit): Unit

  def onUserDataUpdateEvent(listenKey: ListenKey)(
      callback:                        Either[OrderTradeUpdateEvent, AccountUpdateEvent] => Unit
  ): Unit
}
