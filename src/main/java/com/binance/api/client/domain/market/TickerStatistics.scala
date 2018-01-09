package com.binance.api.client.domain.market

/**
  * 24 hour price change statistics for a ticker.
  */
case class TickerStatistics(
    /**
      * Price change during the last 24 hours.
      */
    priceChange: String,
    /**
      * Price change, in percentage, during the last 24 hours.
      */
    priceChangePercent: String,
    /**
      * Weighted average price.
      */
    weightedAvgPrice: String,
    /**
      * Previous close price.
      */
    prevClosePrice: String,
    /**
      * Last price.
      */
    lastPrice: String,
    /**
      * Bid price.
      */
    bidPrice: String,
    /**
      * Ask price.
      */
    askPrice: String,
    /**
      * Open price 24 hours ago.
      */
    openPrice: String,
    /**
      * Highest price during the past 24 hours.
      */
    highPrice: String,
    /**
      * Lowest price during the past 24 hours.
      */
    lowPrice: String,
    /**
      * Total volume during the past 24 hours.
      */
    volume: String,
    /**
      * Open time.
      */
    openTime: Long,
    /**
      * Close time.
      */
    closeTime: Long,
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
