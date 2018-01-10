package com.binance.api.client.domain.account

import com.binance.api.client.domain._

/**
  * Trade order information.
  */
case class Order(
    /**
      * Symbol that the order was put on.
      */
    symbol: Asset,
    /**
      * Order id.
      */
    orderId: OrderId,
    /**
      * Client order id.
      */
    clientOrderId: String,
    /**
      * Price.
      */
    price: Price,
    /**
      * Original quantity.
      */
    origQty:     Quantity,
    executedQty: Quantity,
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
    stopPrice: Price,
    /**
      * Used with iceberg orders.
      */
    icebergQty: Quantity,
    /**
      * Order timestamp.
      */
    time: Instant
)
