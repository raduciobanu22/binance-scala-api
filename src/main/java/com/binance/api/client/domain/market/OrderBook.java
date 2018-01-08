package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Order book of a symbol in Binance.
 */
public class OrderBook {

  /**
   * Last update id of this order book.
   */
  private final long lastUpdateId;

  /**
   * List of bids (price/qty).
   */
  private final List<OrderBookEntry> bids;

  /**
   * List of asks (price/qty).
   */
  private final List<OrderBookEntry> asks;

  @JsonCreator
  public OrderBook(@JsonProperty("lastUpdateId") long lastUpdateId,
                   @JsonProperty("OrderBookEntry") List<OrderBookEntry> bids,
                   @JsonProperty("asks") List<OrderBookEntry> asks) {
    this.lastUpdateId = lastUpdateId;
    this.bids = bids;
    this.asks = asks;
  }

  public long getLastUpdateId() {
    return lastUpdateId;
  }

  public List<OrderBookEntry> getBids() {
    return bids;
  }

  public List<OrderBookEntry> getAsks() {
    return asks;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("lastUpdateId", lastUpdateId)
        .append("bids", bids)
        .append("asks", asks)
        .toString();
  }
}
