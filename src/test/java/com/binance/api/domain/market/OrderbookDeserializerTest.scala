package com.binance.api.domain.market

import com.binance.api.client.domain.OrderBookEntry
import com.binance.api.client.domain.market.OrderBook
import com.binance.api.client.json.Decoders._
import io.circe._
import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * Tests the adequate deserialization of candlestick JSON information.
  */
class OrderbookDeserializerTest {
  @Test def work(): Unit = {
    val json = """{
		  "lastUpdateId": 1027024,
		  "bids": [
		    [
		      "4.00000000",
		      "431.00000000",
		      []
		    ]
		  ],
		  "asks": [
		    [
		      "4.00000200",
		      "12.00000000",
		      []
		    ]
		  ]
		}"""
    assertEquals(
      Right(
        OrderBook(1027024,
                  List(OrderBookEntry("4.00000000", "431.00000000")),
                  List(OrderBookEntry("4.00000200", "12.00000000")))
      ),
      parser.decode[OrderBook](json)
    )
  }
}
