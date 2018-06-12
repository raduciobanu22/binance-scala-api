package com.binance.api.client.domain.market

import com.binance.api.client.domain.{Instant, Price, Quantity, TradeBase}

/**
  * An executed trade for a symbol.
  */
case class Trade(
    /**
      * Trade id.
      */
    tradeId: Long,
    /**
      * Price.
      */
    price: Price,
    /**
      * Quantity.
      */
    quantity: Quantity,
    /**
      * Trade execution time.
      */
    tradeTime:     Option[Instant],
    isBuyerMaker:  Boolean,
    isBestMatch:   Boolean
) extends TradeBase
