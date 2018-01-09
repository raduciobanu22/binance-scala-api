package com.binance.api.client.domain.account.request

/**
  * Request object for canceling an order.
  */
case class CancelOrderRequest(
    symbol: String,
    recvWindow: Option[Long] = None,
    timestamp: Option[Long] = None,
    orderId: Option[Long] = None,
    origClientOrderId: Option[String] = None,
    newClientOrderId: Option[String] = None
)
