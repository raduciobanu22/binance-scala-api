package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.AggTrade;

import java.util.*;

/**
 * Illustrates how to use the aggTrades event stream to create a local cache of trades for a symbol.
 */
public class AggTradesCacheExample {

  /**
   * Key is the aggregate trade id, and the value contains the aggregated trade data, which is
   * automatically updated whenever a new agg data stream event arrives.
   */
  private Map<Long, AggTrade> aggTradesCache;

  public AggTradesCacheExample(String symbol) {
    initializeAggTradesCache(symbol);
    startAggTradesEventStreaming(symbol);
  }

  /**
   * Initializes the aggTrades cache by using the REST API.
   */
  private void initializeAggTradesCache(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiRestClient client = factory.newRestClient();
    List<AggTrade> aggTrades = client.getAggTrades(symbol.toUpperCase(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

    this.aggTradesCache = new HashMap<>();
    for (AggTrade aggTrade : aggTrades) {
      aggTradesCache.put(aggTrade.aggregatedTradeId, aggTrade);
    }
  }

  /**
   * Begins streaming of agg trades events.
   */
  private void startAggTradesEventStreaming(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onAggTradeEvent(symbol.toLowerCase(), response -> {
      aggTradesCache.put(response.aggregatedTradeId, response);
      System.out.println(response);
    });
  }

  /**
   * @return an aggTrades cache, containing the aggregated trade id as the key,
   * and the agg trade data as the value.
   */
  public Map<Long, AggTrade> getAggTradesCache() {
    return aggTradesCache;
  }

  public static void main(String[] args) {
    new AggTradesCacheExample("ETHBTC");
  }
}
