package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Optional.empty;

/**
 * Examples on how to get account information.
 */
public class AccountEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();
    Consumer<Object> print = System.out::println;

    // Get account balances
    client.getAccount(Optional.of(6000000L), empty()).thenAccept(account -> {
      print.accept(account.balances);
      print.accept(account.getAssetBalance("ETH"));
    });

    // Get list of trades
    client.getMyTrades("NEOETH", empty(), empty(), empty(), empty()).thenAccept(print);

    // Get withdraw history
    client.getWithdrawHistory("ETH").thenAccept(print);

    // Get deposit history
    client.getDepositHistory("ETH").thenAccept(print);

    // Get deposit address
    client.getDepositAddress("ETH").thenAccept(print);

    // Withdraw
    client.withdraw("ETH", "0x123", "0.1", empty());
  }
}
