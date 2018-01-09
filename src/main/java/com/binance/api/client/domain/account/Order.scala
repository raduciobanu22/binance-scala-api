package com.binance.api.client.domain.account

import com.binance.api.client.domain.{OrderSide, OrderStatus, OrderType, TimeInForce}

/**
  * Trade order information.
  */
case class Order(
    /**
      * Symbol that the order was put on.
      */
    symbol: String,
    /**
      * Order id.
      */
    orderId: Long,
    /**
      * Client order id.
      */
    clientOrderId: String,
    /**
      * Price.
      */
    price: String,
    /**
      * Original quantity.
      */
    origQty: String,
    executedQty: String,
    /**
      * Order status.
      */
    status: OrderStatus,
    /**
      * Time in force to indicate how long will the order remain active.
      */
    timeInForce: TimeInForce,
    /**
      * Type of order.
      */
    `type`: OrderType,
    /**
      * Buy/Sell order side.
      */
    side: OrderSide,
    /**
      * Used with stop orders.
      */
    stopPrice: String,
    /**
      * Used with iceberg orders.
      */
    icebergQty: String,
    /**
      * Order timestamp.
      */
    time: Long
)
