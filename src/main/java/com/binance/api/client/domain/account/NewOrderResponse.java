package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Response returned when placing a new order on the system.
 *
 * @see NewOrder for the request
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOrderResponse {
  @JsonCreator
  public NewOrderResponse(@JsonProperty("symbol") String symbol,
                          @JsonProperty("orderId") Long orderId,
                          @JsonProperty("clientOrderId") String clientOrderId,
                          @JsonProperty("transactTime") Long transactTime) {
    this.symbol = symbol;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.transactTime = transactTime;
  }

  /**
   * Order symbol.
   */
  private final String symbol;

  /**
   * Order id.
   */
  private final Long orderId;

  /**
   * This will be either a generated one, or the newClientOrderId parameter
   * which was passed when creating the new order.
   */
  private final String clientOrderId;

  /**
   * Transact time for this order.
   */
  private final Long transactTime;

  public String getSymbol() {
    return symbol;
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public Long getTransactTime() {
    return transactTime;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("orderId", orderId)
        .append("clientOrderId", clientOrderId)
        .append("transactTime", transactTime)
        .toString();
  }
}
