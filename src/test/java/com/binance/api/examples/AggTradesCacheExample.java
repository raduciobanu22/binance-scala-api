package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.AggTrade;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Illustrates how to use the aggTrades event stream to create a local cache of trades for a symbol.
 */
public class AggTradesCacheExample {
    public AggTradesCacheExample(BinanceApiAsyncRestClient restClient,
                                 BinanceApiWebSocketClient webSocketClient,
                                 String symbol) {
        initialCache(restClient, symbol)
            .thenAccept(cache -> startAggTradesEventStreaming(webSocketClient, cache, symbol));
    }

    /**
     * Initializes the aggTrades cache by using the REST API.
     *
     * @return Key is the aggregate trade id, and the value contains the aggregated trade data, which is
     * automatically updated whenever a new agg data stream event arrives.
     */
    private CompletableFuture<Map<Long, AggTrade>> initialCache(BinanceApiAsyncRestClient client, String symbol) {
        return client.getAggTrades(symbol.toUpperCase(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()).thenApply(aggTrades -> {
            Map<Long, AggTrade> aggTradesCache = new HashMap<>();
            for (AggTrade aggTrade : aggTrades) {
                aggTradesCache.put(aggTrade.aggregatedTradeId, aggTrade);
            }
            return aggTradesCache;
        });

    }

    /**
     * Begins streaming of agg trades events.
     */
    private void startAggTradesEventStreaming(BinanceApiWebSocketClient webSocketClient, Map<Long, AggTrade> aggTradesCache, String symbol) {
        webSocketClient.onAggTradeEvent(symbol.toLowerCase(), response -> {
            aggTradesCache.put(response.aggregatedTradeId, response);
            System.out.println(response);
        });
    }

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        new AggTradesCacheExample(factory.newAsyncRestClient(), factory.newWebSocketClient(), "ETHBTC");
    }
}
