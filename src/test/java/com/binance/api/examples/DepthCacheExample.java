package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Illustrates how to use the depth event stream to create a local cache of bids/asks for a symbol.
 */
public class DepthCacheExample {
    static class Cache {
        public Cache(long lastUpdateId, NavigableMap<BigDecimal, BigDecimal> bids, NavigableMap<BigDecimal, BigDecimal> asks) {
            this.lastUpdateId = lastUpdateId;
            this.bids = bids;
            this.asks = asks;
        }

        long lastUpdateId;
        NavigableMap<BigDecimal, BigDecimal> bids;
        NavigableMap<BigDecimal, BigDecimal> asks;
    }

    public DepthCacheExample(String symbol) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

        initializeDepthCache(client, symbol)
            .thenAccept(cache -> startDepthEventStreaming(cache, symbol));

    }

    /**
     * Initializes the depth cache by using the REST API.
     */
    private CompletableFuture<Cache> initializeDepthCache(BinanceApiAsyncRestClient client, String symbol) {
        return client.getOrderBook(symbol.toUpperCase(), 10).thenApply((OrderBook orderBook) -> {

            NavigableMap<BigDecimal, BigDecimal> asks = new TreeMap<>(Comparator.reverseOrder());
            for (OrderBookEntry ask : orderBook.asks) {
                asks.put(new BigDecimal(ask.price), new BigDecimal(ask.qty));
            }

            NavigableMap<BigDecimal, BigDecimal> bids = new TreeMap<>(Comparator.reverseOrder());
            for (OrderBookEntry bid : orderBook.bids) {
                bids.put(new BigDecimal(bid.price), new BigDecimal(bid.qty));
            }

            return new Cache(orderBook.lastUpdateId, bids, asks);
        });
    }

    /**
     * Begins streaming of depth events.
     */
    private void startDepthEventStreaming(Cache cache, String symbol) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiWebSocketClient client = factory.newWebSocketClient();

        client.onDepthEvent(symbol.toLowerCase(), response -> {
            if (response.updateId > cache.lastUpdateId) {
                System.out.println(response);
                cache.lastUpdateId = response.updateId;
                updateOrderBook(cache.asks, response.asks);
                updateOrderBook(cache.bids, response.bids);
                printDepthCache(cache);
            }
        });
    }

    /**
     * Updates an order book (bids or asks) with a delta received from the server.
     * <p>
     * Whenever the qty specified is ZERO, it means the price should was removed from the order book.
     */
    private void updateOrderBook(NavigableMap<BigDecimal, BigDecimal> lastOrderBookEntries, List<OrderBookEntry> orderBookDeltas) {
        for (OrderBookEntry orderBookDelta : orderBookDeltas) {
            BigDecimal price = new BigDecimal(orderBookDelta.price);
            BigDecimal qty = new BigDecimal(orderBookDelta.qty);
            if (qty.compareTo(BigDecimal.ZERO) == 0) {
                // qty=0 means remove this level
                lastOrderBookEntries.remove(price);
            } else {
                lastOrderBookEntries.put(price, qty);
            }
        }
    }

    /**
     * @return the best ask in the order book
     */
    private Map.Entry<BigDecimal, BigDecimal> getBestAsk(Cache cache) {
        return cache.asks.lastEntry();
    }

    /**
     * @return the best bid in the order book
     */
    private Map.Entry<BigDecimal, BigDecimal> getBestBid(Cache cache) {
        return cache.bids.firstEntry();
    }

    /**
     * Prints the cached order book / depth of a symbol as well as the best ask and bid price in the book.
     */
    private void printDepthCache(Cache cache) {
        System.out.println("ASKS:");
        cache.asks.entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
        System.out.println("BIDS:");
        cache.bids.entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
        System.out.println("BEST ASK: " + toDepthCacheEntryString(getBestAsk(cache)));
        System.out.println("BEST BID: " + toDepthCacheEntryString(getBestBid(cache)));
    }

    /**
     * Pretty prints an order book entry in the format "price / quantity".
     */
    private static String toDepthCacheEntryString(Map.Entry<BigDecimal, BigDecimal> depthCacheEntry) {
        return depthCacheEntry.getKey().toPlainString() + " / " + depthCacheEntry.getValue();
    }

    public static void main(String[] args) {
        new DepthCacheExample("ETHBTC");
    }
}
