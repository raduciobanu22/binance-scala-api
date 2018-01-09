package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.ListenKey;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

/**
 * Illustrates how to use the user data event stream to create a local cache for the balance of an account.
 */
public class AccountBalanceCacheExample {

    public AccountBalanceCacheExample(String apiKey, String secret) {
        BinanceApiClientFactory clientFactory = BinanceApiClientFactory.newInstance(apiKey, secret);
        BinanceApiAsyncRestClient client = clientFactory.newAsyncRestClient();
        BinanceApiWebSocketClient webSocketClient = clientFactory.newWebSocketClient();

        initializeAssetBalanceCache(client).thenCompose(
            accountBalanceCache ->
                initializedStreamSession(client).thenAccept(
                    listenKey -> startAccountBalanceEventStreaming(webSocketClient, accountBalanceCache, listenKey)
                )
        );
    }

    /**
     * Initializes the asset balance cache by using the REST API and starts a new user data streaming session.
     *
     * @return a listenKey that can be used with the user data streaming API.
     */
    private CompletableFuture<ListenKey> initializedStreamSession(BinanceApiAsyncRestClient client) {
        return client.startUserDataStream();
    }

    /**
     * Initializes the asset balance cache by using the REST API
     */
    private CompletableFuture<Map<String, AssetBalance>> initializeAssetBalanceCache(BinanceApiAsyncRestClient client) {
        return client.getAccount(Optional.empty(), Optional.empty()).thenApply(account -> {
            /**
             * Key is the symbol, and the value is the balance of that symbol on the account.
             */
            Map<String, AssetBalance> accountBalanceCache = new TreeMap<>();

            for (AssetBalance assetBalance : account.balances) {
                accountBalanceCache.put(assetBalance.asset, assetBalance);
            }

            return accountBalanceCache;
        });
    }

    /**
     * Begins streaming of agg trades events.
     */
    private void startAccountBalanceEventStreaming(BinanceApiWebSocketClient webSocketClient,
                                                   Map<String, AssetBalance> accountBalanceCache,
                                                   ListenKey listenKey) {
        webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
            response.accountUpdateEvent.ifPresent(
                (AccountUpdateEvent updated) -> {
                    for (AssetBalance assetBalance : updated.balances) {
                        accountBalanceCache.put(assetBalance.asset, assetBalance);
                    }
                    System.out.println(accountBalanceCache);

                }
            );
        });
    }

    public static void main(String[] args) {
        new AccountBalanceCacheExample("YOUR_API_KEY", "YOUR_SECRET");
    }
}
