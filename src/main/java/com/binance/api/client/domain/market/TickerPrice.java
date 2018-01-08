package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Wraps a symbol and its corresponding latest price.
 */
public class TickerPrice {

  /**
   * Ticker symbol.
   */
  private final String symbol;

  /**
   * Latest price.
   */
  private final String price;

  @JsonCreator
  public TickerPrice(@JsonProperty("symbol") String symbol,
                     @JsonProperty("price") String price) {
    this.symbol = symbol;
    this.price = price;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("price", price)
        .toString();
  }
}
