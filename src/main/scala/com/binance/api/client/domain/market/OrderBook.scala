package com.binance.api.client.domain.market

import com.binance.api.client.domain.OrderBookEntry

/**
  * Order book of a symbol in Binance.
  */
case class OrderBook(
    /**
      * Last update id of this order book.
      */
    lastUpdateId: Long,
    /**
      * List of bids (price/qty).
      */
    bids: List[OrderBookEntry],
    /**
      * List of asks (price/qty).
      */
    asks: List[OrderBookEntry]
)
