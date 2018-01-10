package com.binance.api.client.domain.market

import com.binance.api.client.domain.{AggTradeBase, Instant, Price, Quantity}

/**
  * An aggregated trade event for a symbol.
  */
case class AggTrade(
    aggregatedTradeId:      Long,
    price:                  Price,
    quantity:               Quantity,
    firstBreakdownTradeId:  Long,
    lastBreakdownTradeId:   Long,
    tradeTime:              Option[Instant],
    isBuyerMaker:           Boolean,
    wasTradeBestPriceMatch: Boolean
) extends AggTradeBase
