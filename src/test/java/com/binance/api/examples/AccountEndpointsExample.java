package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.Trade;

import java.util.List;
import java.util.Optional;

/**
 * Examples on how to get account information.
 */
public class AccountEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiRestClient client = factory.newRestClient();

    // Get account balances
    Account account = client.getAccount(Optional.of(6000000L), Optional.empty());
    System.out.println(account.getBalances());
    System.out.println(account.getAssetBalance("ETH"));

    // Get list of trades
    List<Trade> myTrades = client.getMyTrades("NEOETH", Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    System.out.println(myTrades);

    // Get withdraw history
    System.out.println(client.getWithdrawHistory("ETH"));

    // Get deposit history
    System.out.println(client.getDepositHistory("ETH"));

    // Get deposit address
    System.out.println(client.getDepositAddress("ETH"));

    // Withdraw
    client.withdraw("ETH", "0x123", "0.1", Optional.empty());
  }
}
