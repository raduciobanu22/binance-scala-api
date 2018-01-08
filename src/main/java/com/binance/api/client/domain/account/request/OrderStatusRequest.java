package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * A specialized order request with additional filters.
 */
public class OrderStatusRequest extends OrderRequest {
  public final Optional<Long> orderId;
  public final Optional<String> origClientOrderId;

  @JsonCreator
  public OrderStatusRequest(@JsonProperty("symbol") String symbol,
                            @JsonProperty("recvWindow") Optional<Long> recvWindow,
                            @JsonProperty("timestamp") Optional<Long> timestamp,
                            @JsonProperty("orderId") Optional<Long> orderId,
                            @JsonProperty("origClientOrderId") Optional<String> origClientOrderId) {
    super(symbol, recvWindow, timestamp);
    this.orderId = orderId;
    this.origClientOrderId = origClientOrderId;
  }

  public OrderStatusRequest(String symbol, Long orderId) {
    super(symbol);
    this.orderId = Optional.of(orderId);
    this.origClientOrderId = Optional.empty();
  }

  public OrderStatusRequest(String symbol, String origClientOrderId) {
    super(symbol);
    this.orderId = Optional.empty();
    this.origClientOrderId = Optional.of(origClientOrderId);
  }

}
