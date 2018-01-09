package com.binance.api.client.domain.account.request

/**
  * Base request parameters for order-related methods.
  */
case class OrderRequest(
    symbol: String,
    recvWindow: Option[Long] = None,
    timestamp: Option[Long] = None
)
