package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;

import java.util.function.Consumer;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();
    Consumer<Object> print = System.out::println;

    // Test connectivity
    client.ping().thenAccept(print);

    // Check server time
    client.getServerTime().thenAccept(print);

    // Exchange info
    client.getExchangeInfo().thenAccept(exchangeInfo -> {
      System.out.println(exchangeInfo.timezone);
      System.out.println(exchangeInfo.symbols);

      // Obtain symbol information
      SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("ETHBTC");
      System.out.println(symbolInfo.status);

      SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
      System.out.println(priceFilter.minPrice);
      System.out.println(priceFilter.tickSize);
    });
  }
}
