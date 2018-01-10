package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.event.{AccountUpdateEvent, OrderTradeUpdateEvent}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * User data stream endpoints examples.
  * <p>
  * It illustrates how to create a stream to obtain updates on a user account,
  * as well as update on trades/orders on a user account.
  */
object UserDataStreamExample extends App {

  val factory         = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client          = factory.newAsyncRestClient
  val webSocketClient = factory.newWebSocketClient

  // First, we obtain a listenKey which is required to interact with the user data stream
  client.startUserDataStream.foreach(
    listenKey =>
      webSocketClient.onUserDataUpdateEvent(listenKey) {
        case Left(orderTradeUpdateEvent: OrderTradeUpdateEvent) =>
          println(orderTradeUpdateEvent)
          // Print original quantity
          println(orderTradeUpdateEvent.originalQuantity)
          // Or price
          println(orderTradeUpdateEvent.price)
        case Right(accountUpdateEvent: AccountUpdateEvent) =>
          println(accountUpdateEvent.balances)
    }
  )

  println("Waiting for events...")
  // We can keep alive the user data stream
  // client.keepAliveUserDataStream(listenKey);
  // Or we can invalidate it, whenever it is no longer needed
  //        client.closeUserDataStream(listenKey);
}
