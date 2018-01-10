package com.binance.api.client.domain.account.request

import com.binance.api.client.domain.{Instant, OrderId, Symbol}

/**
  * A specialized order request with additional filters.
  */
case class AllOrdersRequest(
    symbol:     Symbol,
    recvWindow: Option[Long] = None,
    timestamp:  Option[Instant] = None,
    orderId:    Option[OrderId] = None,
    limit:      Option[Int] = None
)
