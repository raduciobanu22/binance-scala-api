package com.binance.api.client.domain.account;

import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
  private final String symbol;

  /**
   * Order id.
   */
  private final Long orderId;

  /**
   * Client order id.
   */
  private final String clientOrderId;

  /**
   * Price.
   */
  private final String price;

  /**
   * Original quantity.
   */
  private final String origQty;

  /**
   * Original quantity.
   */
  private final String executedQty;

  /**
   * Order status.
   */
  private final OrderStatus status;

  /**
   * Time in force to indicate how long will the order remain active.
   */
  private final TimeInForce timeInForce;

  /**
   * Type of order.
   */
  private final OrderType type;

  /**
   * Buy/Sell order side.
   */
  private final OrderSide side;

  /**
   * Used with stop orders.
   */
  private final String stopPrice;

  /**
   * Used with iceberg orders.
   */
  private final String icebergQty;

  /**
   * Order timestamp.
   */
  private final long time;

  public String getSymbol() {
    return symbol;
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public String getPrice() {
    return price;
  }

  public String getOrigQty() {
    return origQty;
  }

  public String getExecutedQty() {
    return executedQty;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  public OrderType getType() {
    return type;
  }

  public OrderSide getSide() {
    return side;
  }

  public String getStopPrice() {
    return stopPrice;
  }

  public String getIcebergQty() {
    return icebergQty;
  }

  public long getTime() {
    return time;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("orderId", orderId)
        .append("clientOrderId", clientOrderId)
        .append("price", price)
        .append("origQty", origQty)
        .append("executedQty", executedQty)
        .append("status", status)
        .append("timeInForce", timeInForce)
        .append("type", type)
        .append("side", side)
        .append("stopPrice", stopPrice)
        .append("icebergQty", icebergQty)
        .append("time", time)
        .toString();
  }
}
