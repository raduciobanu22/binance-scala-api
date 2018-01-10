package com.binance.api.domain.event

import com.binance.api.client.domain.event.{CandlestickDetailed, CandlestickEvent}
import com.binance.api.client.json.Decoders._
import io.circe._
import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * Tests that JSON responses from ta candlestick event are converted to the appropriate <code>CandlestickEvent</code> object.
  */
class CandlestickEventDeserializerTest {
  @Test def testCandlestickEventDeserializer(): Unit = {
    val json = s"""{
        "e": "kline",
        "E": 1,
        "s": "ETHBTC",
        "k": {
        "t": 1499404860000,
        "T": 1499404919999,
        "s": "ETHBTC", 
        "i": "1m",
        "f": 77462, 
        "L": 77465, 
        "o": "0.10278577", 
        "c": "0.10278645", 
        "h": "0.10278712", 
        "l": "0.10278518", 
        "v": "17.47929838", 
        "n": 4, 
        "x": false, 
        "q": "1.79662878", 
        "V": "2.34879839", 
        "Q": "0.24142166", 
        "B": "13279784.01349473"
      }}"""
    assertEquals(
      Right(
        CandlestickEvent(
          eventType = "kline",
          eventTime = 1,
          symbol = "ETHBTC",
          candlestick = CandlestickDetailed(
            openTime = 1499404860000L,
            open = "0.10278577",
            high = "0.10278712",
            low = "0.10278518",
            close = "0.10278645",
            volume = "17.47929838",
            closeTime = 1499404919999L,
            intervalId = "1m",
            firstTradeId = 77462,
            lastTradeId = 77465,
            quoteAssetVolume = "1.79662878",
            numberOfTrades = 4,
            takerBuyBaseAssetVolume = "2.34879839",
            takerBuyQuoteAssetVolume = "0.24142166",
            isBarFinal = false
          )
        )
      ),
      parser.decode[CandlestickEvent](json)
    )
  }
}
