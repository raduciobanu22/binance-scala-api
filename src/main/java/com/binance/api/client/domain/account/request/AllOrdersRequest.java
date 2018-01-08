package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  private final Optional<Long> orderId;

  private final Optional<Integer> limit;

  public Optional<Long> getOrderId() {
    return orderId;
  }

  public Optional<Integer> getLimit() {
    return limit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
        .append("orderId", orderId)
        .append("limit", limit)
        .toString();
  }
}
