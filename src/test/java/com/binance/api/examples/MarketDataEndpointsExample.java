package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.exception.BinanceApiException;

import java.util.function.Consumer;

import static com.binance.api.client.domain.market.CandlestickInterval.WEEKLY;
import static java.util.Optional.empty;

/**
 * Examples on how to get market data information such as the latest price of a symbol, etc.
 */
public class MarketDataEndpointsExample {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiAsyncRestClient client = factory.newAsyncRestClient();
        Consumer<Object> print = System.out::println;

        // Getting depth of a symbol
        client.getOrderBook("NEOETH", 10).thenAccept(orderBook -> System.out.println(orderBook.asks));

        // Getting latest price of a symbol
        client.get24HrPriceStatistics("NEOETH").thenAccept(print);

        // Getting all latest prices
        client.getAllPrices().thenAccept(print);

        // Getting agg trades
        client.getAggTrades("NEOETH", empty(), empty(), empty(), empty()).thenAccept(print);

        // Weekly candlestick bars for a symbol
        client.getCandlestickBars("NEOETH", WEEKLY, empty(), empty(), empty()).thenAccept(print);

        // Getting all book tickers
        client.getBookTickers().thenAccept(print);

        // Exception handling
        client.getOrderBook("UNKNOWN", 10).handle((orderBook, th) -> {
            if (th instanceof BinanceApiException) {
                BinanceApiException e = (BinanceApiException) th;
                System.out.println(e.getError().getCode()); // -1121
                System.out.println(e.getError().getMsg());  // Invalid symbol
            }
            return null;
        });
    }
}
