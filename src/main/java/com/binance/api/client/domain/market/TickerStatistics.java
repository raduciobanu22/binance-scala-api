package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 24 hour price change statistics for a ticker.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerStatistics {

  /**
   * Price change during the last 24 hours.
   */
  private final String priceChange;

  /**
   * Price change, in percentage, during the last 24 hours.
   */
  private final String priceChangePercent;

  /**
   * Weighted average price.
   */
  private final String weightedAvgPrice;

  /**
   * Previous close price.
   */
  private final String prevClosePrice;

  /**
   * Last price.
   */
  private final String lastPrice;

  /**
   * Bid price.
   */
  private final String bidPrice;

  /**
   * Ask price.
   */
  private final String askPrice;

  /**
   * Open price 24 hours ago.
   */
  private final String openPrice;

  /**
   * Highest price during the past 24 hours.
   */
  private final String highPrice;

  /**
   * Lowest price during the past 24 hours.
   */
  private final String lowPrice;

  /**
   * Total volume during the past 24 hours.
   */
  private final String volume;

  /**
   * Open time.
   */
  private final long openTime;

  /**
   * Close time.
   */
  private final long closeTime;

  /**
   * First trade id.
   */
  private final long firstId;

  /**
   * Last trade id.
   */
  private final long lastId;

  /**
   * Total number of trades during the last 24 hours.
   */
  private final long count;

  @JsonCreator
  public TickerStatistics(@JsonProperty("priceChange") String priceChange,
                          @JsonProperty("priceChangePercent") String priceChangePercent,
                          @JsonProperty("weightedAvgPrice") String weightedAvgPrice,
                          @JsonProperty("prevClosePrice") String prevClosePrice,
                          @JsonProperty("lastPrice") String lastPrice,
                          @JsonProperty("bidPrice") String bidPrice,
                          @JsonProperty("askPrice") String askPrice,
                          @JsonProperty("openPrice") String openPrice,
                          @JsonProperty("highPrice") String highPrice,
                          @JsonProperty("lowPrice") String lowPrice,
                          @JsonProperty("volume") String volume,
                          @JsonProperty("openTime") long openTime,
                          @JsonProperty("closeTime") long closeTime,
                          @JsonProperty("firstId") long firstId,
                          @JsonProperty("lastId") long lastId,
                          @JsonProperty("count") long count) {
    this.priceChange = priceChange;
    this.priceChangePercent = priceChangePercent;
    this.weightedAvgPrice = weightedAvgPrice;
    this.prevClosePrice = prevClosePrice;
    this.lastPrice = lastPrice;
    this.bidPrice = bidPrice;
    this.askPrice = askPrice;
    this.openPrice = openPrice;
    this.highPrice = highPrice;
    this.lowPrice = lowPrice;
    this.volume = volume;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.firstId = firstId;
    this.lastId = lastId;
    this.count = count;
  }

  public String getPriceChange() {
    return priceChange;
  }

  public String getPriceChangePercent() {
    return priceChangePercent;
  }

  public String getWeightedAvgPrice() {
    return weightedAvgPrice;
  }

  public String getPrevClosePrice() {
    return prevClosePrice;
  }

  public String getLastPrice() {
    return lastPrice;
  }

  public String getBidPrice() {
    return bidPrice;
  }

  public String getAskPrice() {
    return askPrice;
  }

  public String getOpenPrice() {
    return openPrice;
  }

  public String getHighPrice() {
    return highPrice;
  }

  public String getLowPrice() {
    return lowPrice;
  }

  public String getVolume() {
    return volume;
  }

  public long getOpenTime() {
    return openTime;
  }

  public long getCloseTime() {
    return closeTime;
  }

  public long getFirstId() {
    return firstId;
  }

  public long getLastId() {
    return lastId;
  }

  public long getCount() {
    return count;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("priceChange", priceChange)
        .append("priceChangePercent", priceChangePercent)
        .append("weightedAvgPrice", weightedAvgPrice)
        .append("prevClosePrice", prevClosePrice)
        .append("lastPrice", lastPrice)
        .append("bidPrice", bidPrice)
        .append("askPrice", askPrice)
        .append("openPrice", openPrice)
        .append("highPrice", highPrice)
        .append("lowPrice", lowPrice)
        .append("volume", volume)
        .append("openTime", openTime)
        .append("closeTime", closeTime)
        .append("firstId", firstId)
        .append("lastId", lastId)
        .append("count", count)
        .toString();
  }
}
