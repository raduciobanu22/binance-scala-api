package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the best price/qty on the order book for a given symbol.
 */
public class BookTicker {

  /**
   * Ticker symbol.
   */
  public final String symbol;

  /**
   * Bid price.
   */
  public final String bidPrice;

  /**
   * Bid quantity
   */
  public final String bidQty;

  /**
   * Ask price.
   */
  public final String askPrice;

  /**
   * Ask quantity.
   */
  public final String askQty;

  @JsonCreator
  public BookTicker(@JsonProperty("symbol") String symbol,
                    @JsonProperty("bidPrice") String bidPrice,
                    @JsonProperty("bidQty") String bidQty,
                    @JsonProperty("askPrice") String askPrice,
                    @JsonProperty("askQty") String askQty) {
    this.symbol = symbol;
    this.bidPrice = bidPrice;
    this.bidQty = bidQty;
    this.askPrice = askPrice;
    this.askQty = askQty;
  }
}
