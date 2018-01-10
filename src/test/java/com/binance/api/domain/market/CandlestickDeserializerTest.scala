package com.binance.api.domain.market

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
          openTime = 1499040000000L,
          open = "0.01634790",
          high = "0.80000000",
          low = "0.01575800",
          close = "0.01577100",
          volume = "148976.11427815",
          closeTime = 1499644799999L,
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
