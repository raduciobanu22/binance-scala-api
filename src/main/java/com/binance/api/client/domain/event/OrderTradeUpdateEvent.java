package com.binance.api.client.domain.event;

import com.binance.api.client.domain.ExecutionType;
import com.binance.api.client.domain.OrderRejectReason;
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
 * Order or trade report update event.
 *
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTradeUpdateEvent {

  private final String eventType;

  private final long eventTime;

  private final String symbol;

  private final String newClientOrderId;

  /**
   * Buy/Sell order side.
   */
  private final OrderSide side;

  /**
   * Type of order.
   */
  private final OrderType type;

  /**
   * Time in force to indicate how long will the order remain active.
   */
  private final TimeInForce timeInForce;

  /**
   * Original quantity in the order.
   */
  private final String originalQuantity;

  /**
   * Price.
   */
  private final String price;

  /**
   * Type of execution.
   */
  private final ExecutionType executionType;

  /**
   * Status of the order.
   */
  private final OrderStatus orderStatus;

  /**
   * Reason why the order was rejected.
   */
  private final OrderRejectReason orderRejectReason;

  /**
   * Order id.
   */
  private final Long orderId;

  /**
   * Quantity of the last filled trade.
   */
  private final String quantityLastFilledTrade;

  /**
   * Accumulated quantity of filled trades on this order.
   */
  private final String accumulatedQuantity;

  /**
   * Price of last filled trade.
   */
  private final String priceOfLastFilledTrade;

  /**
   * Commission.
   */
  private final String commission;

  /**
   * Asset on which commission is taken
   */
  private final String commissionAsset;

  /**
   * Order/trade time.
   */
  private final Long orderTradeTime;

  /**
   * Trade id.
   */
  private final Long tradeId;

  @JsonCreator
  public OrderTradeUpdateEvent(@JsonProperty("e") String eventType,
                               @JsonProperty("E") long eventTime,
                               @JsonProperty("s") String symbol,
                               @JsonProperty("c") String newClientOrderId,
                               @JsonProperty("S") OrderSide side,
                               @JsonProperty("o") OrderType type,
                               @JsonProperty("f") TimeInForce timeInForce,
                               @JsonProperty("q") String originalQuantity,
                               @JsonProperty("p") String price,
                               @JsonProperty("x") ExecutionType executionType,
                               @JsonProperty("X") OrderStatus orderStatus,
                               @JsonProperty("r") OrderRejectReason orderRejectReason,
                               @JsonProperty("i") Long orderId,
                               @JsonProperty("l") String quantityLastFilledTrade,
                               @JsonProperty("z") String accumulatedQuantity,
                               @JsonProperty("L") String priceOfLastFilledTrade,
                               @JsonProperty("n") String commission,
                               @JsonProperty("N") String commissionAsset,
                               @JsonProperty("T") Long orderTradeTime,
                               @JsonProperty("t") Long tradeId) {
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
    this.newClientOrderId = newClientOrderId;
    this.side = side;
    this.type = type;
    this.timeInForce = timeInForce;
    this.originalQuantity = originalQuantity;
    this.price = price;
    this.executionType = executionType;
    this.orderStatus = orderStatus;
    this.orderRejectReason = orderRejectReason;
    this.orderId = orderId;
    this.quantityLastFilledTrade = quantityLastFilledTrade;
    this.accumulatedQuantity = accumulatedQuantity;
    this.priceOfLastFilledTrade = priceOfLastFilledTrade;
    this.commission = commission;
    this.commissionAsset = commissionAsset;
    this.orderTradeTime = orderTradeTime;
    this.tradeId = tradeId;
  }

  public String getEventType() {
    return eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getNewClientOrderId() {
    return newClientOrderId;
  }

  public OrderSide getSide() {
    return side;
  }

  public OrderType getType() {
    return type;
  }

  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  public String getOriginalQuantity() {
    return originalQuantity;
  }

  public String getPrice() {
    return price;
  }

  public ExecutionType getExecutionType() {
    return executionType;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public OrderRejectReason getOrderRejectReason() {
    return orderRejectReason;
  }

  public long getOrderId() {
    return orderId;
  }

  public String getQuantityLastFilledTrade() {
    return quantityLastFilledTrade;
  }

  public String getAccumulatedQuantity() {
    return accumulatedQuantity;
  }

  public String getPriceOfLastFilledTrade() {
    return priceOfLastFilledTrade;
  }

  public String getCommission() {
    return commission;
  }

  public String getCommissionAsset() {
    return commissionAsset;
  }

  public long getOrderTradeTime() {
    return orderTradeTime;
  }

  public long getTradeId() {
    return tradeId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime)
        .append("symbol", symbol)
        .append("newClientOrderId", newClientOrderId)
        .append("side", side)
        .append("type", type)
        .append("timeInForce", timeInForce)
        .append("originalQuantity", originalQuantity)
        .append("price", price)
        .append("executionType", executionType)
        .append("orderStatus", orderStatus)
        .append("orderRejectReason", orderRejectReason)
        .append("orderId", orderId)
        .append("quantityLastFilledTrade", quantityLastFilledTrade)
        .append("accumulatedQuantity", accumulatedQuantity)
        .append("priceOfLastFilledTrade", priceOfLastFilledTrade)
        .append("commission", commission)
        .append("commissionAsset", commissionAsset)
        .append("orderTradeTime", orderTradeTime)
        .append("tradeId", tradeId)
        .toString();
  }
}
