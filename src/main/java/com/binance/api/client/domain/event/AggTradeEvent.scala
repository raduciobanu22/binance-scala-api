package com.binance.api.client.domain.event

import com.binance.api.client.domain.AggTradeBase

/**
  * An aggregated trade event for a symbol.
  */
case class AggTradeEvent(
    eventType: String,
    eventTime: Long,
    symbol: String,
    aggregatedTradeId: Long,
    price: String,
    quantity: String,
    firstBreakdownTradeId: Long,
    lastBreakdownTradeId: Long,
    tradeTime: Option[Long],
    isBuyerMaker: Boolean,
    wasTradeBestPriceMatch: Boolean
) extends AggTradeBase
