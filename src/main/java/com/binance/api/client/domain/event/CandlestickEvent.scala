package com.binance.api.client.domain.event

import com.binance.api.client.domain.CandlestickBase

/**
  * An interval candlestick for a symbol providing information on price that can be used to produce candlestick charts.
  */
case class CandlestickEvent(
    eventType: String,
    eventTime: Long,
    symbol: String,
    candlestick: CandlestickDetailed,
)

case class CandlestickDetailed(
    openTime: Long,
    open: String,
    high: String,
    low: String,
    close: String,
    volume: String,
    closeTime: Long,
    intervalId: String,
    firstTradeId: Long,
    lastTradeId: Long,
    quoteAssetVolume: String,
    numberOfTrades: Long,
    takerBuyBaseAssetVolume: String,
    takerBuyQuoteAssetVolume: String,
    isBarFinal: Boolean
) extends CandlestickBase
