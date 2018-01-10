package com.binance.api.client.domain.market

import com.binance.api.client.domain.{Price, Quantity, Symbol}

/**
  * Represents the best price/qty on the order book for a given symbol.
  */
case class BookTicker(
    /**
      * Ticker symbol.
      */
    symbol: Symbol,
    /**
      * Bid price.
      */
    bidPrice: Price,
    /**
      * Bid quantity
      */
    bidQty: Quantity,
    /**
      * Ask price.
      */
    askPrice: Price,
    /**
      * Ask quantity.
      */
    askQty: Quantity
)
