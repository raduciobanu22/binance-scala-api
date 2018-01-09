package com.binance.api.domain.account

import junit.framework.TestCase._
import com.binance.api.client.domain.account.{Withdraw, WithdrawHistory}
import io.circe._
import junit.framework.TestCase._
import org.junit.Test
import com.binance.api.client.domain.account.AccountJson._

/**
  * Test deserialization of a withdraw/deposit history.
  */
class WithdrawHistoryDeserializerTest {
  @Test def testWithdrawHistoryDeserialziation(): Unit = {
    val json = """{
  "withdrawList": [
    {
      "amount": 0.1,
      "address": "0x456",
      "successTime": "2017-10-13 21:20:09",
      "txId": "0x123",
      "id": "1",
      "asset": "ETH",
      "applyTime": "2017-10-13 20:59:38",
      "userId": "1",
      "status": 6
    }
  ],
  "success": true
}"""

    assertEquals(
      Right(
        WithdrawHistory(
          withdrawList = List(
            Withdraw(amount = 0.1,
                     address = "0x456",
                     asset = "ETH",
                     applyTime = "2017-10-13 20:59:38",
                     successTime = "2017-10-13 21:20:09",
                     txId = "0x123",
                     id = "1",
                     status = 6)
          ),
          success = true
        )
      ),
      parser.decode[WithdrawHistory](json)
    )
  }
}
