package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A specialized order request with additional filters.
 */
public class OrderStatusRequest extends OrderRequest {
  @JsonCreator
  public OrderStatusRequest(@JsonProperty("symbol") String symbol,
                            @JsonProperty("recvWindow") Long recvWindow,
                            @JsonProperty("timestamp") Long timestamp,
                            @JsonProperty("orderId") Long orderId,
                            @JsonProperty("origClientOrderId") String origClientOrderId) {
    super(symbol, recvWindow, timestamp);
    this.orderId = orderId;
    this.origClientOrderId = origClientOrderId;
  }

  public OrderStatusRequest(String symbol, Long orderId, String origClientOrderId) {
    super(symbol);
    this.orderId = orderId;
    this.origClientOrderId = origClientOrderId;
  }

  private Long orderId;

  private String origClientOrderId;

  public OrderStatusRequest(String symbol, Long orderId) {
    super(symbol);
    this.orderId = orderId;
  }

  public OrderStatusRequest(String symbol, String origClientOrderId) {
    super(symbol);
    this.origClientOrderId = origClientOrderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public OrderStatusRequest orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public String getOrigClientOrderId() {
    return origClientOrderId;
  }

  public OrderStatusRequest origClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("orderId", orderId)
        .append("origClientOrderId", origClientOrderId)
        .toString();
  }
}
