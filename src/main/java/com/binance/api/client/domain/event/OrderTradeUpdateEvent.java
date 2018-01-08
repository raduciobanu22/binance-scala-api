package com.binance.api.client.domain.event;

import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Order or trade report update event.
 *
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTradeUpdateEvent {

  public final String eventType;

  public final long eventTime;

  public final String symbol;

  public final String newClientOrderId;

  /**
   * Buy/Sell order side.
   */
  public final OrderSide side;

  /**
   * Type of order.
   */
  public final OrderType type;

  /**
   * Time in force to indicate how long will the order remain active.
   */
  public final TimeInForce timeInForce;

  /**
   * Original quantity in the order.
   */
  public final String originalQuantity;

  /**
   * Price.
   */
  public final String price;

  /**
   * Type of execution.
   */
  public final ExecutionType executionType;

  /**
   * Status of the order.
   */
  public final OrderStatus orderStatus;

  /**
   * Reason why the order was rejected.
   */
  public final OrderRejectReason orderRejectReason;

  /**
   * Order id.
   */
  public final Long orderId;

  /**
   * Quantity of the last filled trade.
   */
  public final String quantityLastFilledTrade;

  /**
   * Accumulated quantity of filled trades on this order.
   */
  public final String accumulatedQuantity;

  /**
   * Price of last filled trade.
   */
  public final String priceOfLastFilledTrade;

  /**
   * Commission.
   */
  public final String commission;

  /**
   * Asset on which commission is taken
   */
  public final String commissionAsset;

  /**
   * Order/trade time.
   */
  public final Long orderTradeTime;

  /**
   * Trade id.
   */
  public final Long tradeId;

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
}
