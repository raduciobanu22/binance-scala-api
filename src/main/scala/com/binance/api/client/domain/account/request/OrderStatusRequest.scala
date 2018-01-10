package com.binance.api.client.domain.account.request

import com.binance.api.client.domain.{Instant, OrderId, Symbol}

/**
  * A specialized order request with additional filters.
  */
case class OrderStatusRequest(
    symbol:            Symbol,
    recvWindow:        Option[Long] = None,
    timestamp:         Option[Instant] = None,
    orderId:           Option[OrderId] = None,
    origClientOrderId: Option[String] = None
)
