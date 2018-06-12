package com.binance.api.client.domain.event

import com.binance.api.client.domain.{Instant, OrderBookEntry, Symbol}

/**
  * Depth delta event for a symbol.
  */
case class DepthEvent(
    eventType: String,
    eventTime: Instant,
    symbol:    Symbol,
    firstUpdateId: Long,
    lastUpdateId: Long,
    /**
      * Bid depth delta. Each price is a 'price level'.
      */
    bids: List[OrderBookEntry],
    /**
      * Ask depth delta.. Each price is a 'price level'.
      */
    asks: List[OrderBookEntry]
)
