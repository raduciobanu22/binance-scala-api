package com.binance.api.client.domain.event;

import com.binance.api.client.domain.market.OrderBookEntry;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Depth delta event for a symbol.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthEvent {
  @JsonCreator
  public DepthEvent(@JsonProperty("e") String eventType,
                    @JsonProperty("E") long eventTime,
                    @JsonProperty("s") String symbol,
                    @JsonProperty("u") long updateId,
                    @JsonProperty("b") List<OrderBookEntry> bids,
                    @JsonProperty("a") List<OrderBookEntry> asks) {
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
    this.updateId = updateId;
    this.bids = bids;
    this.asks = asks;
  }

  public final String eventType;

  public final long eventTime;

  public final String symbol;

  /**
   * updateId to sync up with updateid in /api/v1/depth
   */
  public final long updateId;

  /**
   * Bid depth delta.
   */
  public final List<OrderBookEntry> bids;

  /**
   * Ask depth delta.
   */
  public final List<OrderBookEntry> asks;


}
