package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExampleAsync {

  public static void main(String[] args) throws InterruptedException {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Test connectivity
    client.ping(response -> System.out.println("Ping succeeded."));

    // Check server time
    client.getServerTime(response -> System.out.println(response.serverTime));

    // Exchange info
    client.getExchangeInfo(exchangeInfo -> {
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
