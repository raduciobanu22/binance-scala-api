package com.binance.api.client.domain.event

import com.binance.api.client.domain._

/**
  * Order or trade report update event.
  *
  * This event is embedded as part of a user data update event.
  *
  * @see UserDataUpdateEvent
  */
case class OrderTradeUpdateEvent(
    eventType: String,
    eventTime: Long,
    symbol: String,
    newClientOrderId: String,
    /**
      * Buy/Sell order side.
      */
    side: OrderSide,
    /**
      * Type of order.
      */
    `type`: OrderType,
    /**
      * Time in force to indicate how long will the order remain active.
      */
    timeInForce: TimeInForce,
    /**
      * Original quantity in the order.
      */
    originalQuantity: String,
    /**
      * Price.
      */
    price: String,
    /**
      * Type of execution.
      */
    executionType: ExecutionType,
    /**
      * Status of the order.
      */
    orderStatus: OrderStatus,
    /**
      * Reason why the order was rejected.
      */
    orderRejectReason: OrderRejectReason,
    /**
      * Order id.
      */
    orderId: Long,
    /**
      * Quantity of the last filled trade.
      */
    quantityLastFilledTrade: String,
    /**
      * Accumulated quantity of filled trades on this order.
      */
    accumulatedQuantity: String,
    /**
      * Price of last filled trade.
      */
    priceOfLastFilledTrade: String,
    /**
      * Commission.
      */
    commission: String,
    /**
      * Asset on which commission is taken
      */
    commissionAsset: Option[String],
    /**
      * Order/trade time.
      */
    orderTradeTime: Long,
    /**
      * Trade id.
      */
    tradeId: Long
)
