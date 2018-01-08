package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An interval candlestick for a symbol providing informations on price that can be used to produce candlestick charts.
 */
@JsonDeserialize(using = CandlestickEventDeserializer.class)
public class CandlestickEvent {
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
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
    this.openTime = openTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.closeTime = closeTime;
    this.intervalId = intervalId;
    this.firstTradeId = firstTradeId;
    this.lastTradeId = lastTradeId;
    this.quoteAssetVolume = quoteAssetVolume;
    this.numberOfTrades = numberOfTrades;
    this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    this.isBarFinal = isBarFinal;
  }

  private final String eventType;

  private final long eventTime;

  private final String symbol;

  private final Long openTime;

  private final String open;

  private final String high;

  private final String low;

  private final String close;

  private final String volume;

  private final Long closeTime;

  private final String intervalId;
  private final Long firstTradeId;
  private final Long lastTradeId;

  private final String quoteAssetVolume;

  private final Long numberOfTrades;

  private final String takerBuyBaseAssetVolume;

  private final String takerBuyQuoteAssetVolume;

  private final Boolean isBarFinal;

  public String getEventType() {
    return eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public Long getOpenTime() {
    return openTime;
  }

  public String getOpen() {
    return open;
  }

  public String getHigh() {
    return high;
  }

  public String getLow() {
    return low;
  }

  public String getClose() {
    return close;
  }

  public String getVolume() {
    return volume;
  }

  public Long getCloseTime() {
    return closeTime;
  }

  public String getIntervalId() {
    return intervalId;
  }

  public Long getFirstTradeId() {
    return firstTradeId;
  }

  public Long getLastTradeId() {
    return lastTradeId;
  }

  public String getQuoteAssetVolume() {
    return quoteAssetVolume;
  }

  public Long getNumberOfTrades() {
    return numberOfTrades;
  }

  public String getTakerBuyBaseAssetVolume() {
    return takerBuyBaseAssetVolume;
  }

  public String getTakerBuyQuoteAssetVolume() {
    return takerBuyQuoteAssetVolume;
  }

  public Boolean getBarFinal() {
    return isBarFinal;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime)
        .append("symbol", symbol)
        .append("openTime", openTime)
        .append("open", open)
        .append("high", high)
        .append("low", low)
        .append("close", close)
        .append("volume", volume)
        .append("closeTime", closeTime)
        .append("intervalId", intervalId)
        .append("firstTradeId", firstTradeId)
        .append("lastTradeId", lastTradeId)
        .append("quoteAssetVolume", quoteAssetVolume)
        .append("numberOfTrades", numberOfTrades)
        .append("takerBuyBaseAssetVolume", takerBuyBaseAssetVolume)
        .append("takerBuyQuoteAssetVolume", takerBuyQuoteAssetVolume)
        .append("isBarFinal", isBarFinal)
        .toString();
  }
}