package com.binance.api.domain.general

import com.binance.api.client.domain.OrderType
import com.binance.api.client.json.Decoders._
import com.binance.api.client.domain.general._
import io.circe._
import org.junit.Assert._
import org.junit.Test

/**
  * Test deserialization of exchange information.
  */
class ExchangeInfoDeserializerTest {
  @Test def testExchangeInfoDeserialization(): Unit = {
    val json = """{
  "timezone": "UTC",
  "serverTime": 1508631584636,
  "rateLimits": [{
      "rateLimitType": "REQUESTS",
      "interval": "MINUTE",
      "limit": 1200
    },
    {
      "rateLimitType": "ORDERS",
      "interval": "SECOND",
      "limit": 10
    },
    {
      "rateLimitType": "ORDERS",
      "interval": "DAY",
      "limit": 100000
    }
  ],
  "exchangeFilters": [],
  "symbols": [{
    "symbol": "ETHBTC",
    "status": "TRADING",
    "baseAsset": "ETH",
    "baseAssetPrecision": 8,
    "quoteAsset": "BTC",
    "quotePrecision": 8,
    "orderTypes": ["LIMIT", "MARKET"],
    "icebergAllowed": false,
    "filters": [{
      "filterType": "PRICE_FILTER",
      "minPrice": "0.00000100",
      "maxPrice": "100000.00000000",
      "tickSize": "0.00000100"
    }, {
      "filterType": "LOT_SIZE",
      "minQty": "0.00100000",
      "maxQty": "100000.00000000",
      "stepSize": "0.00100000"
    }, {
      "filterType": "MIN_NOTIONAL",
      "minNotional": "0.00100000"
    }]
  }]}"""
    assertEquals(
      Right(
        ExchangeInfo(
          timezone = "UTC",
          serverTime = 1508631584636L,
          rateLimits = List(
            RateLimit(rateLimitType = RateLimitType.REQUESTS, interval = RateLimitInterval.MINUTE, limit = 1200),
            RateLimit(rateLimitType = RateLimitType.ORDERS, interval = RateLimitInterval.SECOND, limit = 10),
            RateLimit(rateLimitType = RateLimitType.ORDERS, interval = RateLimitInterval.DAY, limit = 100000)
          ),
          symbols = List(
            SymbolInfo(
              symbol = "ETHBTC",
              status = SymbolStatus.TRADING,
              baseAsset = "ETH",
              baseAssetPrecision = 8,
              quoteAsset = "BTC",
              quotePrecision = 8,
              orderTypes = List(OrderType.LIMIT, OrderType.MARKET),
              icebergAllowed = false,
              filters = List(
                SymbolFilter(
                  FilterType.PRICE_FILTER,
                  minPrice = Some("0.00000100"),
                  maxPrice = Some("100000.00000000"),
                  tickSize = Some("0.00000100"),
                  minQty = None,
                  maxQty = None,
                  stepSize = None,
                  minNotional = None,
                  limit = None
                ),
                SymbolFilter(
                  FilterType.LOT_SIZE,
                  minPrice = None,
                  maxPrice = None,
                  tickSize = None,
                  minQty = Some("0.00100000"),
                  maxQty = Some("100000.00000000"),
                  stepSize = Some("0.00100000"),
                  minNotional = None,
                  limit = None
                ),
                SymbolFilter(
                  filterType = FilterType.MIN_NOTIONAL,
                  minPrice = None,
                  maxPrice = None,
                  tickSize = None,
                  minQty = None,
                  maxQty = None,
                  stepSize = None,
                  minNotional = Some("0.00100000"),
                  limit = None
                )
              )
            )
          )
        )
      ),
      parser.decode[ExchangeInfo](json)
    )
  }
}
