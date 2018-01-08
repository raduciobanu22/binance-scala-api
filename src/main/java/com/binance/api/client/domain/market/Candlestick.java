package com.binance.api.client.domain.market;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
 */
@JsonDeserialize(using = CandlestickDeserializer.class)
public class Candlestick {

  private final Long openTime;
  private final String open;
  private final String high;
  private final String low;
  private final String close;
  private final String volume;
  private final Long closeTime;
  private final String quoteAssetVolume;
  private final Long numberOfTrades;
  private final String takerBuyBaseAssetVolume;
  private final String takerBuyQuoteAssetVolume;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("openTime", openTime)
        .append("open", open)
        .append("high", high)
        .append("low", low)
        .append("close", close)
        .append("volume", volume)
        .append("closeTime", closeTime)
        .append("quoteAssetVolume", quoteAssetVolume)
        .append("numberOfTrades", numberOfTrades)
        .append("takerBuyBaseAssetVolume", takerBuyBaseAssetVolume)
        .append("takerBuyQuoteAssetVolume", takerBuyQuoteAssetVolume)
        .toString();
  }
}
