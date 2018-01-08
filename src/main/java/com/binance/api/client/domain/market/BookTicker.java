package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents the best price/qty on the order book for a given symbol.
 */
public class BookTicker {

  /**
   * Ticker symbol.
   */
  private final String symbol;

  /**
   * Bid price.
   */
  private final String bidPrice;

  /**
   * Bid quantity
   */
  private final String bidQty;

  /**
   * Ask price.
   */
  private final String askPrice;

  /**
   * Ask quantity.
   */
  private final String askQty;

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

  public String getSymbol() {
    return symbol;
  }

  public String getBidPrice() {
    return bidPrice;
  }

  public String getBidQty() {
    return bidQty;
  }

  public String getAskPrice() {
    return askPrice;
  }

  public String getAskQty() {
    return askQty;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("bidPrice", bidPrice)
        .append("bidQty", bidQty)
        .append("askPrice", askPrice)
        .append("askQty", askQty)
        .toString();
  }
}
