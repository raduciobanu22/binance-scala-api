package com.binance.api.client.domain.event;

import com.binance.api.client.domain.market.Candlestick;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * An interval candlestick for a symbol providing informations on price that can be used to produce candlestick charts.
 */
@JsonDeserialize(using = CandlestickEventDeserializer.class)
public class CandlestickEvent extends Candlestick {
  public CandlestickEvent(String eventType,
                          long eventTime,
                          String symbol,
                          Long openTime,
                          String open,
                          String high,
                          String low,
                          String close,
                          String volume,
                          Long closeTime,
                          String intervalId,
                          Long firstTradeId,
                          Long lastTradeId,
                          String quoteAssetVolume,
                          Long numberOfTrades,
                          String takerBuyBaseAssetVolume,
                          String takerBuyQuoteAssetVolume,
                          Boolean isBarFinal) {
    super(openTime, open, high, low, close, volume, closeTime, quoteAssetVolume, numberOfTrades, takerBuyBaseAssetVolume, takerBuyQuoteAssetVolume);
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
    this.intervalId = intervalId;
    this.firstTradeId = firstTradeId;
    this.lastTradeId = lastTradeId;
    this.isBarFinal = isBarFinal;
  }

  public final String eventType;
  public final long eventTime;
  public final String symbol;
  public final String intervalId;
  public final Long firstTradeId;
  public final Long lastTradeId;
  public final Boolean isBarFinal;
}