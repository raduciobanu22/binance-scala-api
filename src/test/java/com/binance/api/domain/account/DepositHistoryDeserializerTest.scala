package com.binance.api.domain.account

import com.binance.api.client.domain.account.{Deposit, DepositHistory}
import io.circe._
import junit.framework.TestCase._
import org.junit.Test
import com.binance.api.client.json.Decoders._

/**
  * Test deserialization of a withdraw/deposit history.
  */
class DepositHistoryDeserializerTest {
  @Test def shouldWork(): Unit = {
    val json =
      """{
    "depositList": [
        {
            "insertTime": 1508198532000,
            "amount": 0.04670582,
            "asset": "ETH",
            "address": "0x6915f16f8791d0a1cc2bf47c13a6b2a92000504b",
            "txId": "0xdf33b22bdb2b28b1f75ccd201a4a4m6e7g83jy5fc5d5a9d1340961598cfcb0a1",
            "status": 1
        },
        {
            "insertTime": 1508298532000,
            "amount": 1000,
            "asset": "XMR",
            "address": "463tWEBn5XZJSxLU34r6g7h8jtxuNcDbjLSjkn3XAXHCbLrTTErJrBWYgHJQyrCwkNgYvyV3z8zctJLPCZy24jvb3NiTcTJ",
            "addressTag": "342341222",
            "txId": "b3c6219639c8ae3f9cf010cdc24fw7f7yt8j1e063f9b4bd1a05cb44c4b6e2509",
            "status": 1
        }
    ],
    "success": true
}
		"""

    assertEquals(
      Right(
        DepositHistory(
          depositList = List(
            Deposit(amount = 0.04670582,
                    asset = "ETH",
                    insertTime = 1508198532000L,
                    txId = "0xdf33b22bdb2b28b1f75ccd201a4a4m6e7g83jy5fc5d5a9d1340961598cfcb0a1",
                    status = 1),
            Deposit(amount = 1000,
                    asset = "XMR",
                    insertTime = 1508298532000L,
                    txId = "b3c6219639c8ae3f9cf010cdc24fw7f7yt8j1e063f9b4bd1a05cb44c4b6e2509",
                    status = 1)
          ),
          success = true
        )
      ),
      parser.decode[DepositHistory](json),
    )
  }
}
