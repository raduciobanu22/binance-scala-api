package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wraps a symbol and its corresponding latest price.
 */
public class TickerPrice {

  /**
   * Ticker symbol.
   */
  public final String symbol;

  /**
   * Latest price.
   */
  public final String price;

  @JsonCreator
  public TickerPrice(@JsonProperty("symbol") String symbol,
                     @JsonProperty("price") String price) {
    this.symbol = symbol;
    this.price = price;
  }
}
