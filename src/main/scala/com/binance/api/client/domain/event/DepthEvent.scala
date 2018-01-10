package com.binance.api.client.domain.event

import com.binance.api.client.domain.{Instant, OrderBookEntry, Symbol}

/**
  * Depth delta event for a symbol.
  */
case class DepthEvent(
    eventType: String,
    eventTime: Instant,
    symbol:    Symbol,
    /**
      * updateId to sync up with updateid in /api/v1/depth
      */
    updateId: Long,
    /**
      * Bid depth delta.
      */
    bids: List[OrderBookEntry],
    /**
      * Ask depth delta.
      */
    asks: List[OrderBookEntry]
)
