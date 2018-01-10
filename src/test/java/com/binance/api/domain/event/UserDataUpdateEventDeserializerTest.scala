package com.binance.api.domain.event

import com.binance.api.client.domain._
import com.binance.api.client.json.Decoders._
import com.binance.api.client.domain.event.{AccountUpdateEvent, OrderTradeUpdateEvent}
import io.circe._
import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * Tests that JSON responses from the stream API are converted to the appropriate object.
  */
class UserDataUpdateEventDeserializerTest {
  @Test def testAccountUpdateEventDeserializer(): Unit = {
    val json = """{
  "e": "outboundAccountInfo",
  "E": 1,
  "m": 10,
  "t": 10,
  "b": 0,
  "s": 0,
  "T": true,
  "W": true,
  "D": true,
  "B": [
    {
      "a": "BTC",
      "f": "0.00000000",
      "l": "0.00000000"
    },
    {
      "a": "LTC",
      "f": "0.00000000",
      "l": "0.00000000"
    },
    {
      "a": "ETH",
      "f": "0.10000000",
      "l": "0.00000000"
    },
    {
      "a": "ENG",
      "f": "0.00000000",
      "l": "0.00000000"
    },
    {
      "a": "ZEC",
      "f": "0.00000000",
      "l": "0.00000000"
    }
  ]
}"""
    assertEquals(
      Right(
        AccountUpdateEvent(
          "outboundAccountInfo",
          1,
          List(
            AssetBalance("BTC", "0.00000000", "0.00000000"),
            AssetBalance("LTC", "0.00000000", "0.00000000"),
            AssetBalance("ETH", "0.10000000", "0.00000000"),
            AssetBalance("ENG", "0.00000000", "0.00000000"),
            AssetBalance("ZEC", "0.00000000", "0.00000000")
          )
        )
      ),
      parser.decode[AccountUpdateEvent](json)
    )
  }

  @Test def testOrderUpdateEventDeserializer(): Unit = {
    val json =
      """{
  "e": "executionReport",
  "E": 1,
  "s": "NEOETH",
  "c": "XXX",
  "S": "BUY",
  "o": "LIMIT",
  "f": "GTC",
  "q": "1000.00000000",
  "p": "0.00010000",
  "P": "0.00000000",
  "F": "0.00000000",
  "g": -1,
  "C": "5yairWLqfzbusOUdPyG712",
  "x": "CANCELED",
  "X": "CANCELED",
  "r": "NONE",
  "i": 123456,
  "l": "0.00000000",
  "z": "0.00000000",
  "L": "0.00000000",
  "n": "0",
  "N": null,
  "T": 1,
  "t": -1,
  "I": 1,
  "w": false,
  "m": false,
  "M": false
}"""
    assertEquals(
      Right(
        OrderTradeUpdateEvent(
          eventType = "executionReport",
          eventTime = 1L,
          symbol = "NEOETH",
          newClientOrderId = "XXX",
          side = OrderSide.BUY,
          `type` = OrderType.LIMIT,
          timeInForce = TimeInForce.GTC,
          originalQuantity = "1000.00000000",
          price = "0.00010000",
          executionType = ExecutionType.CANCELED,
          orderStatus = OrderStatus.CANCELED,
          orderRejectReason = OrderRejectReason.NONE,
          orderId = 123456,
          quantityLastFilledTrade = "0.00000000",
          accumulatedQuantity = "0.00000000",
          priceOfLastFilledTrade = "0.00000000",
          commission = "0",
          commissionAsset = None,
          orderTradeTime = 1,
          tradeId = -1
        )
      ),
      parser.decode[OrderTradeUpdateEvent](json)
    )
  }
}
