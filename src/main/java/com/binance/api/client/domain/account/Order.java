package com.binance.api.client.domain.account;

import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Trade order information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
  @JsonCreator
  public Order(@JsonProperty("symbol") String symbol,
               @JsonProperty("orderId") Long orderId,
               @JsonProperty("clientOrderId") String clientOrderId,
               @JsonProperty("price") String price,
               @JsonProperty("origQty") String origQty,
               @JsonProperty("executedQty") String executedQty,
               @JsonProperty("status") OrderStatus status,
               @JsonProperty("timeInForce") TimeInForce timeInForce,
               @JsonProperty("type") OrderType type,
               @JsonProperty("side") OrderSide side,
               @JsonProperty("stopPrice") String stopPrice,
               @JsonProperty("icebergQty") String icebergQty,
               @JsonProperty("time") long time) {
    this.symbol = symbol;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.price = price;
    this.origQty = origQty;
    this.executedQty = executedQty;
    this.status = status;
    this.timeInForce = timeInForce;
    this.type = type;
    this.side = side;
    this.stopPrice = stopPrice;
    this.icebergQty = icebergQty;
    this.time = time;
  }

  /**
   * Symbol that the order was put on.
   */
  public final String symbol;

  /**
   * Order id.
   */
  public final Long orderId;

  /**
   * Client order id.
   */
  public final String clientOrderId;

  /**
   * Price.
   */
  public final String price;

  /**
   * Original quantity.
   */
  public final String origQty;

  /**
   * Original quantity.
   */
  public final String executedQty;

  /**
   * Order status.
   */
  public final OrderStatus status;

  /**
   * Time in force to indicate how long will the order remain active.
   */
  public final TimeInForce timeInForce;

  /**
   * Type of order.
   */
  public final OrderType type;

  /**
   * Buy/Sell order side.
   */
  public final OrderSide side;

  /**
   * Used with stop orders.
   */
  public final String stopPrice;

  /**
   * Used with iceberg orders.
   */
  public final String icebergQty;

  /**
   * Order timestamp.
   */
  public final long time;


}
