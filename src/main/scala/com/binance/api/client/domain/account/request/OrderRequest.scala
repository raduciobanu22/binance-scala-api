package com.binance.api.client.domain.account.request

import com.binance.api.client.domain.{Instant, Symbol}

/**
  * Base request parameters for order-related methods.
  */
case class OrderRequest(
    symbol:     Symbol,
    recvWindow: Option[Long] = None,
    timestamp:  Option[Instant] = None
)
