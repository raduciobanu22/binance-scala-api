package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;

/**
 * User data stream endpoints examples.
 *
 * It illustrates how to create a stream to obtain updates on a user account,
 * as well as update on trades/orders on a user account.
 */
public class UserDataStreamExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiRestClient client = factory.newRestClient();

    // First, we obtain a listenKey which is required to interact with the user data stream
    String listenKey = client.startUserDataStream();

    // Then, we open a new web socket client, and provide a callback that is called on every update
    BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();

    // Listen for changes in the account
    webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
      response.accountUpdateEvent.ifPresent(accountUpdateEvent -> {
        // Print new balances of every available asset
        System.out.println(accountUpdateEvent.balances);
      });

      response.orderTradeUpdateEvent.ifPresent(orderTradeUpdateEvent -> {
        // Print details about an order/trade
        System.out.println(orderTradeUpdateEvent);

        // Print original quantity
        System.out.println(orderTradeUpdateEvent.originalQuantity);

        // Or price
        System.out.println(orderTradeUpdateEvent.price);

      });
    });
    System.out.println("Waiting for events...");

    // We can keep alive the user data stream
    // client.keepAliveUserDataStream(listenKey);

    // Or we can invalidate it, whenever it is no longer needed
    // client.closeUserDataStream(listenKey);
  }
}
