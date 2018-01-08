package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Base request parameters for order-related methods.
 */
public class OrderRequest {
  @JsonCreator
  public OrderRequest(@JsonProperty("symbol") String symbol,
                      @JsonProperty("recvWindow") Optional<Long> recvWindow,
                      @JsonProperty("timestamp") Optional<Long> timestamp) {
    this.symbol = symbol;
    this.recvWindow = recvWindow;
    this.timestamp = timestamp;
  }

  public OrderRequest(String symbol) {
    this(symbol, Optional.empty(), Optional.empty());
  }

  public final String symbol;

  public final Optional<Long> recvWindow;

  public final Optional<Long> timestamp;

}
