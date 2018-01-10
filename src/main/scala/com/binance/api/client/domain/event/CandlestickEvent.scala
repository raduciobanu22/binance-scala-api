package com.binance.api.client.domain.event

import com.binance.api.client.domain._

/**
  * An interval candlestick for a symbol providing information on price that can be used to produce candlestick charts.
  */
case class CandlestickEvent(
    eventType:   String,
    eventTime:   Instant,
    symbol:      Symbol,
    candlestick: CandlestickDetailed,
)

case class CandlestickDetailed(
    openTime:                 Instant,
    open:                     Price,
    high:                     Price,
    low:                      Price,
    close:                    Price,
    volume:                   Volume,
    closeTime:                Instant,
    intervalId:               String,
    firstTradeId:             Long,
    lastTradeId:              Long,
    quoteAssetVolume:         String,
    numberOfTrades:           Long,
    takerBuyBaseAssetVolume:  String,
    takerBuyQuoteAssetVolume: String,
    isBarFinal:               Boolean
) extends CandlestickBase
