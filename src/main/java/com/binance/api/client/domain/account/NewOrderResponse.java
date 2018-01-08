package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
  public final String symbol;

  /**
   * Order id.
   */
  public final Long orderId;

  /**
   * This will be either a generated one, or the newClientOrderId parameter
   * which was passed when creating the new order.
   */
  public final String clientOrderId;

  /**
   * Transact time for this order.
   */
  public final Long transactTime;

}
