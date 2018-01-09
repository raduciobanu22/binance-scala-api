package com.binance.api.client.domain.event

import com.binance.api.client.domain.OrderBookEntry

/**
  * Depth delta event for a symbol.
  */
case class DepthEvent(
    eventType: String,
    eventTime: Long,
    symbol: String,
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
