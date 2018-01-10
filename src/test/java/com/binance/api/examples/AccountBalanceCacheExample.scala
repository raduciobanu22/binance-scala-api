package com.binance.api.examples

import java.util

import com.binance.api.client.domain.{AssetBalance, Asset}
import com.binance.api.client.domain.event.{AccountUpdateEvent, ListenKey}
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Illustrates how to use the user data event stream to create a local cache for the balance of an account.
  */
object AccountBalanceCacheExample extends App {
  val factory         = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client          = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient

  /**
    * Initializes the asset balance cache by using the REST API
    */
  private def initializeAssetBalanceCache(
      client: BinanceApiAsyncRestClient
  ): Future[util.TreeMap[Asset, AssetBalance]] =
    client
      .getAccount()
      .map(account => {
        println(account)

        /**
          * Key is the symbol, and the value is the balance of that symbol on the account.
          */
        val accountBalanceCache = new util.TreeMap[Asset, AssetBalance]
        for (assetBalance <- account.balances) {
          accountBalanceCache.put(assetBalance.asset, assetBalance)
        }
        accountBalanceCache
      })

  /**
    * Begins streaming of agg trades events.
    */
  private def startAccountBalanceEventStreaming(webSocketClient:     BinanceApiWebSocketClient,
                                                accountBalanceCache: util.Map[Asset, AssetBalance],
                                                listenKey:           ListenKey): Unit =
    webSocketClient.onUserDataUpdateEvent(listenKey) {
      case Right(updated: AccountUpdateEvent) =>
        for (assetBalance <- updated.balances) {
          accountBalanceCache.put(assetBalance.asset, assetBalance)
        }
        println(accountBalanceCache)
      case _ => ()
    }

  initializeAssetBalanceCache(client)
    .flatMap(
      accountBalanceCache =>
        client.startUserDataStream.map(
          listenKey => startAccountBalanceEventStreaming(webSocketClient, accountBalanceCache, listenKey)
      )
    )
}
