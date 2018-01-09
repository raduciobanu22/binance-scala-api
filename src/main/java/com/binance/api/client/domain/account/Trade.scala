package com.binance.api.client.domain.account

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
    price: String,
    /**
      * Quantity.
      */
    qty: String,
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
    time: Long,
    buyer: Boolean,
    maker: Boolean,
    bestMatch: Boolean,
    orderId: String
)
