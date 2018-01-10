package com.binance.api.client.domain.market

import com.binance.api.client.domain._

/**
  * 24 hour price change statistics for a ticker.
  */
case class TickerStatistics(
    /**
      * Price change during the last 24 hours.
      */
    priceChange: PriceDiff,
    /**
      * Price change, in percentage, during the last 24 hours.
      */
    priceChangePercent: Percent,
    /**
      * Weighted average price.
      */
    weightedAvgPrice: Price,
    /**
      * Previous close price.
      */
    prevClosePrice: Price,
    /**
      * Last price.
      */
    lastPrice: Price,
    /**
      * Bid price.
      */
    bidPrice: Price,
    /**
      * Ask price.
      */
    askPrice: Price,
    /**
      * Open price 24 hours ago.
      */
    openPrice: Price,
    /**
      * Highest price during the past 24 hours.
      */
    highPrice: Price,
    /**
      * Lowest price during the past 24 hours.
      */
    lowPrice: Price,
    /**
      * Total volume during the past 24 hours.
      */
    volume: Volume,
    /**
      * Open time.
      */
    openTime: Instant,
    /**
      * Close time.
      */
    closeTime: Instant,
    /**
      * First trade id.
      */
    firstId: Long,
    /**
      * Last trade id.
      */
    lastId: Long,
    /**
      * Total number of trades during the last 24 hours.
      */
    count: Long
)
