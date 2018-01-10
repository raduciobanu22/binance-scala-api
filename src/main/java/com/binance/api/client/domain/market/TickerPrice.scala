package com.binance.api.client.domain.market

import com.binance.api.client.domain.{Price, Symbol}

/**
  * Wraps a symbol and its corresponding latest price.
  */
case class TickerPrice(
    /**
      * Ticker symbol.
      */
    symbol: Symbol,
    /**
      * Latest price.
      */
    price: Price
)
