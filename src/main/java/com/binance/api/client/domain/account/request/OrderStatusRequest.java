package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

/**
 * A specialized order request with additional filters.
 */
public class OrderStatusRequest extends OrderRequest {
  private final Optional<Long> orderId;
  private final Optional<String> origClientOrderId;

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

  public Optional<Long> getOrderId() {
    return orderId;
  }

  public Optional<String> getOrigClientOrderId() {
    return origClientOrderId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("orderId", orderId)
        .append("origClientOrderId", origClientOrderId)
        .toString();
  }
}
