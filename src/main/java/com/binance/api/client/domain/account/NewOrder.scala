package com.binance.api.client.domain.account

import com.binance.api.client.domain.{OrderSide, OrderType, TimeInForce}

/**
  * A trade order to enter or exit a position.
  */
object NewOrder {

  /**
    * Places a MARKET buy order for the given <code>quantity</code>.
    *
    * @return a new order which is pre-configured with MARKET as the order type and BUY as the order side.
    */
  def marketBuy(symbol: String, quantity: String) =
    new NewOrder(symbol, OrderSide.BUY, OrderType.MARKET, None, quantity)

  /**
    * Places a MARKET sell order for the given <code>quantity</code>.
    *
    * @return a new order which is pre-configured with MARKET as the order type and SELL as the order side.
    */
  def marketSell(symbol: String, quantity: String) =
    new NewOrder(symbol, OrderSide.SELL, OrderType.MARKET, None, quantity)

  /**
    * Places a LIMIT buy order for the given <code>quantity</code> and <code>price</code>.
    *
    * @return a new order which is pre-configured with LIMIT as the order type and BUY as the order side.
    */
  def limitBuy(symbol: String, timeInForce: TimeInForce, quantity: String, price: String) =
    new NewOrder(symbol, OrderSide.BUY, OrderType.LIMIT, Some(timeInForce), quantity, Some(price))

  /**
    * Places a LIMIT sell order for the given <code>quantity</code> and <code>price</code>.
    *
    * @return a new order which is pre-configured with LIMIT as the order type and SELL as the order side.
    */
  def limitSell(symbol: String, timeInForce: TimeInForce, quantity: String, price: String) =
    new NewOrder(symbol, OrderSide.SELL, OrderType.LIMIT, Some(timeInForce), quantity, Some(price))
}

case class NewOrder(
    /**
      * Symbol to place the order on.
      */
    symbol: String,
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
    timeInForce: Option[TimeInForce],
    /**
      * Quantity.
      */
    quantity: String,
    /**
      * Price.
      */
    price: Option[String] = None,
    /**
      * A unique id for the order. Automatically generated if not sent.
      */
    newClientOrderId: Option[String] = None,
    /**
      * Used with stop orders.
      */
    stopPrice: Option[String] = None,
    /**
      * Used with iceberg orders.
      */
    icebergQty: Option[String] = None,
    /**
      * Receiving window.
      */
    recvWindow: Option[Long] = None,
    /**
      * Order timestamp.
      */
    timestamp: Option[Long] = None
)
