package com.binance.api.client.domain.account.request

import com.binance.api.client.domain.{Instant, OrderId, Symbol}

/**
  * Request object for canceling an order.
  */
case class CancelOrderRequest(
    symbol:            Symbol,
    recvWindow:        Option[Long] = None,
    timestamp:         Option[Instant] = None,
    orderId:           Option[OrderId] = None,
    origClientOrderId: Option[String] = None,
    newClientOrderId:  Option[String] = None
)
