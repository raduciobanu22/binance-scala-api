package com.binance.api.client.domain.account

/**
  * Response returned when placing a new order on the system.
  *
  * @see NewOrder for the request
  */
case class NewOrderResponse(
    /**
      * Order symbol.
      */
    symbol: String,
    /**
      * Order id.
      */
    orderId: Long,
    /**
      * This will be either a generated one, or the newClientOrderId parameter
      * which was passed when creating the new order.
      */
    clientOrderId: String,
    /**
      * Transact time for this order.
      */
    transactTime: Long
)
