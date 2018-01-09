package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

import static java.util.Optional.empty;

/**
 * Illustrates how to use the klines/candlesticks event stream to create a local cache of bids/asks for a symbol.
 */
public class CandlesticksCacheExample {

  public CandlesticksCacheExample(String symbol, CandlestickInterval interval) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();
    BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();

    initializeCandlestickCache(client, symbol, interval).thenAccept(
        cache -> startCandlestickEventStreaming(webSocketClient, cache, symbol, interval));
  }

  /**
   * Initializes the candlestick cache by using the REST API.
   */
  private CompletableFuture<Map<Long, Candlestick>> initializeCandlestickCache(BinanceApiAsyncRestClient client, String symbol, CandlestickInterval interval) {
    return client.getCandlestickBars(symbol.toUpperCase(), interval, empty(), empty(), empty()).thenApply(
        candlestickBars -> {
          Map<Long, Candlestick> candlesticksCache = new TreeMap<>();
          for (Candlestick candlestickBar : candlestickBars) {
            candlesticksCache.put(candlestickBar.openTime, candlestickBar);
          }
          return candlesticksCache;
        });
  }

  /**
   * Begins streaming of depth events.
   */
  private void startCandlestickEventStreaming(BinanceApiWebSocketClient client,
                                              Map<Long, Candlestick> candlesticksCache,
                                              String symbol,
                                              CandlestickInterval interval) {
    client.onCandlestickEvent(symbol.toLowerCase(), interval, response -> {
      candlesticksCache.put(response.openTime, response);
      System.out.println(response);
    });
  }

  public static void main(String[] args) {
    new CandlesticksCacheExample("ETHBTC", CandlestickInterval.ONE_MINUTE);
  }
}
