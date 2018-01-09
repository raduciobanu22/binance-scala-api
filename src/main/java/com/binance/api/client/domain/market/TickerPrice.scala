package com.binance.api.client.domain.market

/**
  * Wraps a symbol and its corresponding latest price.
  */
case class TickerPrice(
    /**
      * Ticker symbol.
      */
    symbol: String,
    /**
      * Latest price.
      */
    price: String
)
