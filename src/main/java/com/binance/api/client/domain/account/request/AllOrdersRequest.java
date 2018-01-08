package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * A specialized order request with additional filters.
 */

public class AllOrdersRequest extends OrderRequest {
  @JsonCreator
  public AllOrdersRequest(@JsonProperty("symbol") String symbol,
                          @JsonProperty("recvWindow") Optional<Long> recvWindow,
                          @JsonProperty("timestamp") Long timestamp,
                          @JsonProperty("orderId") Optional<Long> orderId,
                          @JsonProperty("limit") Optional<Integer> limit) {
    super(symbol, recvWindow, Optional.of(timestamp));
    this.orderId = orderId;
    this.limit = limit;
  }

  public AllOrdersRequest(String symbol, Optional<Long> orderId, Optional<Integer> limit) {
    super(symbol);
    this.orderId = orderId;
    this.limit = limit;
  }

  public final Optional<Long> orderId;

  public final Optional<Integer> limit;

}
