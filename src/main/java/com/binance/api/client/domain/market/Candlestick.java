package com.binance.api.client.domain.market;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
 */
@JsonDeserialize(using = CandlestickDeserializer.class)
public class Candlestick {

  public final Long openTime;
  public final String open;
  public final String high;
  public final String low;
  public final String close;
  public final String volume;
  public final Long closeTime;
  public final String quoteAssetVolume;
  public final Long numberOfTrades;
  public final String takerBuyBaseAssetVolume;
  public final String takerBuyQuoteAssetVolume;

  public Candlestick(Long openTime,
                     String open,
                     String high,
                     String low,
                     String close,
                     String volume,
                     Long closeTime,
                     String quoteAssetVolume,
                     Long numberOfTrades,
                     String takerBuyBaseAssetVolume,
                     String takerBuyQuoteAssetVolume) {
    this.openTime = openTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.closeTime = closeTime;
    this.quoteAssetVolume = quoteAssetVolume;
    this.numberOfTrades = numberOfTrades;
    this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
  }
}
