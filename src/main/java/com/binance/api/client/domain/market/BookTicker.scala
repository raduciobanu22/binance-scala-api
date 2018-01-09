package com.binance.api.client.domain.market

/**
  * Represents the best price/qty on the order book for a given symbol.
  */
case class BookTicker(
    /**
      * Ticker symbol.
      */
    symbol: String,
    /**
      * Bid price.
      */
    bidPrice: String,
    /**
      * Bid quantity
      */
    bidQty: String,
    /**
      * Ask price.
      */
    askPrice: String,
    /**
      * Ask quantity.
      */
    askQty: String
)
