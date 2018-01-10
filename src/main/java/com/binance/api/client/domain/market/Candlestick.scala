package com.binance.api.client.domain.market

import com.binance.api.client.domain.{CandlestickBase, Instant, Price, Volume}

/**
  * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
  */
case class Candlestick(
    openTime:                 Instant,
    open:                     Price,
    high:                     Price,
    low:                      Price,
    close:                    Price,
    volume:                   Volume,
    closeTime:                Instant,
    quoteAssetVolume:         String,
    numberOfTrades:           Long,
    takerBuyBaseAssetVolume:  String,
    takerBuyQuoteAssetVolume: String
) extends CandlestickBase
