package com.binance.api.client.domain.account.request

/**
  * A specialized order request with additional filters.
  */
case class OrderStatusRequest(
    symbol: String,
    recvWindow: Option[Long] = None,
    timestamp: Option[Long] = None,
    orderId: Option[Long] = None,
    origClientOrderId: Option[String] = None
)
