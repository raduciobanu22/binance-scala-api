package com.binance.api.examples

import java.util

import com.binance.api.client.domain.AssetBalance
import com.binance.api.client.domain.event.{AccountUpdateEvent, ListenKey}
import com.binance.api.client.{BinanceApiAsyncRestClient, BinanceApiClientFactory, BinanceApiWebSocketClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Illustrates how to use the user data event stream to create a local cache for the balance of an account.
  */
object AccountBalanceCacheExample extends App {
  val clientFactory   = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client          = clientFactory.newAsyncRestClient
  val webSocketClient = clientFactory.newWebSocketClient

  /**
    * Initializes the asset balance cache by using the REST API and starts a new user data streaming session.
    *
    * @return a listenKey that can be used with the user data streaming API.
    */
  private def initializedStreamSession(client: BinanceApiAsyncRestClient): Future[ListenKey] =
    client.startUserDataStream

  /**
    * Initializes the asset balance cache by using the REST API
    */
  private def initializeAssetBalanceCache(
      client: BinanceApiAsyncRestClient
  ): Future[util.TreeMap[String, AssetBalance]] =
    client
      .getAccount()
      .map(account => {

        /**
          * Key is the symbol, and the value is the balance of that symbol on the account.
          */
        val accountBalanceCache = new util.TreeMap[String, AssetBalance]
        for (assetBalance <- account.balances) {
          accountBalanceCache.put(assetBalance.asset, assetBalance)
        }
        accountBalanceCache
      })

  /**
    * Begins streaming of agg trades events.
    */
  private def startAccountBalanceEventStreaming(webSocketClient:     BinanceApiWebSocketClient,
                                                accountBalanceCache: util.Map[String, AssetBalance],
                                                listenKey:           ListenKey): Unit =
    webSocketClient.onUserDataUpdateEvent(listenKey) {
      case Right(updated: AccountUpdateEvent) =>
        for (assetBalance <- updated.balances) {
          accountBalanceCache.put(assetBalance.asset, assetBalance)
        }
        println(accountBalanceCache)
      case _ => ()
    }

  initializeAssetBalanceCache(client).flatMap(
    (accountBalanceCache) =>
      initializedStreamSession(client).map(
        listenKey => startAccountBalanceEventStreaming(webSocketClient, accountBalanceCache, listenKey)
      )
  )
}
