package com.binance.api.client.domain.event

import com.binance.api.client.domain._

/**
  * A trade event for a symbol.
  */
case class TradeEvent(
    eventType:              String,
    eventTime:              Instant,
    symbol:                 Symbol,
    tradeId:                Long,
    price:                  Price,
    quantity:               Quantity,
    buyerOrderId:           Long,
    sellerOrderId:          Long,
    tradeTime:              Option[Instant],
    isBuyerMaker:           Boolean,
    isBestMatch:            Boolean
) extends TradeBase
