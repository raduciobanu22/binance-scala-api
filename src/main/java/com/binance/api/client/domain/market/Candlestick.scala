package com.binance.api.client.domain.market

import com.binance.api.client.domain.CandlestickBase

/**
  * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
  */
case class Candlestick(
    openTime:                 Long,
    open:                     String,
    high:                     String,
    low:                      String,
    close:                    String,
    volume:                   String,
    closeTime:                Long,
    quoteAssetVolume:         String,
    numberOfTrades:           Long,
    takerBuyBaseAssetVolume:  String,
    takerBuyQuoteAssetVolume: String
) extends CandlestickBase
