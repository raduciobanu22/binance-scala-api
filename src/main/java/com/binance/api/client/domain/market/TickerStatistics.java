package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 24 hour price change statistics for a ticker.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerStatistics {

  /**
   * Price change during the last 24 hours.
   */
  public final String priceChange;

  /**
   * Price change, in percentage, during the last 24 hours.
   */
  public final String priceChangePercent;

  /**
   * Weighted average price.
   */
  public final String weightedAvgPrice;

  /**
   * Previous close price.
   */
  public final String prevClosePrice;

  /**
   * Last price.
   */
  public final String lastPrice;

  /**
   * Bid price.
   */
  public final String bidPrice;

  /**
   * Ask price.
   */
  public final String askPrice;

  /**
   * Open price 24 hours ago.
   */
  public final String openPrice;

  /**
   * Highest price during the past 24 hours.
   */
  public final String highPrice;

  /**
   * Lowest price during the past 24 hours.
   */
  public final String lowPrice;

  /**
   * Total volume during the past 24 hours.
   */
  public final String volume;

  /**
   * Open time.
   */
  public final long openTime;

  /**
   * Close time.
   */
  public final long closeTime;

  /**
   * First trade id.
   */
  public final long firstId;

  /**
   * Last trade id.
   */
  public final long lastId;

  /**
   * Total number of trades during the last 24 hours.
   */
  public final long count;

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
}
