package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.exception.BinanceApiException;

import java.util.Optional;
import java.util.function.Consumer;

import static com.binance.api.client.domain.account.NewOrder.limitBuy;
import static com.binance.api.client.domain.account.NewOrder.marketBuy;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class OrdersExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();
    Consumer<Object> print = System.out::println;

    // Getting list of open orders
    client.getOpenOrders(new OrderRequest("LINKETH")).thenAccept(print);

    // Getting list of all orders with a limit of 10
    client.getAllOrders(new AllOrdersRequest("LINKETH", Optional.empty(), Optional.of(10))).thenAccept(print);

    // Get status of a particular order
    client.getOrderStatus(new OrderStatusRequest("LINKETH", 751698L)).thenAccept(print);

    // Canceling an order
    try {
      client.cancelOrder(new CancelOrderRequest("LINKETH", 756762l));
    } catch (BinanceApiException e) {
      System.out.println(e.getError().getMsg());
    }

    // Placing a test LIMIT order
    client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"));

    // Placing a test MARKET order
    client.newOrderTest(marketBuy("LINKETH", "1000"));

    // Placing a real LIMIT order
    client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001")).thenAccept(print);
  }

}
