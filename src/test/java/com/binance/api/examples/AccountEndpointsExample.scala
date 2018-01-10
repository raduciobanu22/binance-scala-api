package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.{Amount, Asset, Symbol}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Examples on how to get account information.
  */
object AccountEndpointsExample extends App {
  val factory = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client  = factory.newAsyncRestClient
  val ETH     = Asset("ETH")

  // Get account balances
  client.getAccount(recvWindow = Some(6000000L)).foreach { account =>
    println(account.balances)
    println(account.getAssetBalance(ETH))
  }
  // Get list of trades
  client.getMyTrades(Symbol("NEOETH")).foreach(println)
  // Get withdraw history
  client.getWithdrawHistory(ETH).foreach(println)
  // Get deposit history
  client.getDepositHistory(ETH).foreach(_.depositList.foreach(println))
  // Get deposit address
  client.getDepositAddress(ETH).foreach(println)
  // Withdraw
  client.withdraw(ETH, "0x123", Amount(BigDecimal("0.1")))
}
