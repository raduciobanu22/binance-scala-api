package com.binance.api.client.domain.event

import com.binance.api.client.domain._

/**
  * An aggregated trade event for a symbol.
  */
case class AggTradeEvent(
    eventType:              String,
    eventTime:              Instant,
    symbol:                 Symbol,
    aggregatedTradeId:      Long,
    price:                  Price,
    quantity:               Quantity,
    firstBreakdownTradeId:  Long,
    lastBreakdownTradeId:   Long,
    tradeTime:              Option[Instant],
    isBuyerMaker:           Boolean,
    wasTradeBestPriceMatch: Boolean
) extends AggTradeBase
