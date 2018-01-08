package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Order book of a symbol in Binance.
 */
public class OrderBook {

  /**
   * Last update id of this order book.
   */
  public final long lastUpdateId;

  /**
   * List of bids (price/qty).
   */
  public final List<OrderBookEntry> bids;

  /**
   * List of asks (price/qty).
   */
  public final List<OrderBookEntry> asks;

  @JsonCreator
  public OrderBook(@JsonProperty("lastUpdateId") long lastUpdateId,
                   @JsonProperty("OrderBookEntry") List<OrderBookEntry> bids,
                   @JsonProperty("asks") List<OrderBookEntry> asks) {
    this.lastUpdateId = lastUpdateId;
    this.bids = bids;
    this.asks = asks;
  }
}
