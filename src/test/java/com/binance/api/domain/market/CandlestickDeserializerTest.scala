package com.binance.api.domain.market

import com.binance.api.client.domain.{Instant, Price, Volume}
import com.binance.api.client.domain.market.Candlestick
import io.circe._
import org.junit.Assert.assertEquals
import org.junit.Test
import com.binance.api.client.json.Decoders._

/**
  * Tests the adequate deserialization of candlestick JSON information.
  */
class CandlestickDeserializerTest {
  @Test def testCandlestickDeserializerTest(): Unit = {
    val json = """[
    1499040000000,
        "0.01634790",
        "0.80000000",
        "0.01575800",
        "0.01577100",
        "148976.11427815",
        1499644799999,
        "2434.19055334",
        308,
        "1756.87402397",
        "28.46694368",
        "17928899.62484339"
        ]"""
    assertEquals(
      Right(
        Candlestick(
          openTime = Instant(1499040000000L),
          open = Price(BigDecimal("0.01634790")),
          high = Price(BigDecimal("0.80000000")),
          low = Price(BigDecimal("0.01575800")),
          close = Price(BigDecimal("0.01577100")),
          volume = Volume(BigDecimal("148976.11427815")),
          closeTime = Instant(1499644799999L),
          quoteAssetVolume = "2434.19055334",
          numberOfTrades = 308,
          takerBuyBaseAssetVolume = "1756.87402397",
          takerBuyQuoteAssetVolume = "28.46694368"
        )
      ),
      parser.decode[Candlestick](json)
    )
  }
}
