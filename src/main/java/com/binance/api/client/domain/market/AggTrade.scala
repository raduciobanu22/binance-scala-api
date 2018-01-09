package com.binance.api.client.domain.market

import com.binance.api.client.domain.AggTradeBase

/**
  * An aggregated trade event for a symbol.
  */
case class AggTrade(
    aggregatedTradeId:      Long,
    price:                  String,
    quantity:               String,
    firstBreakdownTradeId:  Long,
    lastBreakdownTradeId:   Long,
    tradeTime:              Option[Long],
    isBuyerMaker:           Boolean,
    wasTradeBestPriceMatch: Boolean
) extends AggTradeBase
