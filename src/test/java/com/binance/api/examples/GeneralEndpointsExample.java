package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiRestClient client = factory.newRestClient();

    // Test connectivity
    client.ping();

    // Check server time
    long serverTime = client.getServerTime();
    System.out.println(serverTime);

    // Exchange info
    ExchangeInfo exchangeInfo = client.getExchangeInfo();
    System.out.println(exchangeInfo.timezone);
    System.out.println(exchangeInfo.symbols);

    // Obtain symbol information
    SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("ETHBTC");
    System.out.println(symbolInfo.status);

    SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
    System.out.println(priceFilter.minPrice);
    System.out.println(priceFilter.tickSize);
  }
}
