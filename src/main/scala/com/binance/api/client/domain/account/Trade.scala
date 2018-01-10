package com.binance.api.client.domain.account

import com.binance.api.client.domain.{Instant, OrderId, Price, Quantity}

/**
  * Represents an executed trade.
  */
case class Trade(
    /**
      * Trade id.
      */
    id: Long,
    /**
      * Price.
      */
    price: Price,
    /**
      * Quantity.
      */
    qty: Quantity,
    /**
      * Commission.
      */
    commission: String,
    /**
      * Asset on which commission is taken
      */
    commissionAsset: String,
    /**
      * Trade execution time.
      */
    time:      Instant,
    buyer:     Boolean,
    maker:     Boolean,
    bestMatch: Boolean,
    orderId:   OrderId
)
