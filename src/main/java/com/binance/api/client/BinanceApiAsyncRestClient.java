package com.binance.api.client;

import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.ServerTime;
import com.binance.api.client.domain.market.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Binance API façade, supporting asynchronous/non-blocking access Binance's REST API.
 */
public interface BinanceApiAsyncRestClient {

  // General endpoints

  /**
   * Test connectivity to the Rest API.
   */
  CompletableFuture<Void> ping();

  /**
   * Check server time.
   */
  CompletableFuture<ServerTime> getServerTime();

  /**
   * Current exchange trading rules and symbol information
   */
  CompletableFuture<ExchangeInfo> getExchangeInfo();

  // Market Data endpoints

  /**
   * Get order book of a symbol (asynchronous)
   *
   * @param symbol ticker symbol (e.g. ETHBTC)
   */
  CompletableFuture<OrderBook> getOrderBook(String symbol, Integer limit);

  /**
   * Get compressed, aggregate trades. Trades that fill at the time, from the same order, with
   * the same price will have the quantity aggregated.
   *
   * If both <code>startTime</code> and <code>endTime</code> are sent, <code>limit</code>should not
   * be sent AND the distance between <code>startTime</code> and <code>endTime</code> must be less than 24 hours.
   *
   * @param symbol symbol to aggregate (mandatory)
   * @param fromId ID to get aggregate trades from INCLUSIVE (optional)
   * @param limit Default 500; max 500 (optional)
   * @param startTime Timestamp in ms to get aggregate trades from INCLUSIVE (optional).
   * @return a list of aggregate trades for the given symbol
   */
  CompletableFuture<List<AggTrade>> getAggTrades(String symbol, Optional<String> fromId, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime);

  /**
  /**
   * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
   *
   * @param symbol symbol to aggregate (mandatory)
   * @param interval candlestick interval (mandatory)
   * @param limit Default 500; max 500 (optional)
   * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
   */
  CompletableFuture<List<Candlestick>> getCandlestickBars(String symbol, CandlestickInterval interval, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime);

  /**
   * Get 24 hour price change statistics (asynchronous).
   *
   */
  CompletableFuture<TickerStatistics> get24HrPriceStatistics(String symbol);

  /**
   * Get Latest price for all symbols (asynchronous).
   */
  CompletableFuture<List<TickerPrice>> getAllPrices();

  /**
   * Get best price/qty on the order book for all symbols (asynchronous).
   */
  CompletableFuture<List<BookTicker>> getBookTickers();

  // Account endpoints

  /**
   * Send in a new order (asynchronous)
   *
   */
  CompletableFuture<NewOrderResponse> newOrder(NewOrder order);

  /**
   * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
   *
   */
  CompletableFuture<Void> newOrderTest(NewOrder order);

  /**
   * Check an order's status (asynchronous).
   *
   */
  CompletableFuture<Order> getOrderStatus(OrderStatusRequest orderStatusRequest);

  /**
   * Cancel an active order (asynchronous).
   *
   */
  CompletableFuture<Void> cancelOrder(CancelOrderRequest cancelOrderRequest);

  /**
   * Get all open orders on a symbol (asynchronous).
   *
   */
  CompletableFuture<List<Order>> getOpenOrders(OrderRequest orderRequest);

  /**
   * Get all account orders; active, canceled, or filled.
   *
   */
  CompletableFuture<List<Order>> getAllOrders(AllOrdersRequest orderRequest);

  /**
   * Get current account information (async).
   */
  CompletableFuture<Account> getAccount(Optional<Long> recvWindow, Optional<Long> timestamp);

  /**
   * Get trades for a specific account and symbol.
   *
   * @param symbol symbol to get trades from
   * @param limit default 500; max 500
   */
  CompletableFuture<List<Trade>> getMyTrades(String symbol, Optional<Integer> limit, Optional<Long> fromId, Optional<Long> recvWindow, Optional<Long> timestamp);

  /**
   * Submit a withdraw request.
   *
   * Enable Withdrawals option has to be active in the API settings.
   *
   * @param asset asset symbol to withdraw
   * @param address address to withdraw to
   * @param amount amount to withdraw
   * @param name description/alias of the address
   */
  CompletableFuture<Void> withdraw(String asset, String address, String amount, Optional<String> name);

  /**
   * Fetch account deposit history.
   */
  CompletableFuture<DepositHistory> getDepositHistory(String asset);

  /**
   * Fetch account withdraw history.
   */
  CompletableFuture<WithdrawHistory> getWithdrawHistory(String asset);

  /**
   * Fetch deposit address.
   */
   CompletableFuture<DepositAddress> getDepositAddress(String asset);

  // User stream endpoints

  /**
   * Start a new user data stream.
   */
  CompletableFuture<ListenKey> startUserDataStream();

  /**
   * PING a user data stream to prevent a time out.
   *
   */
  CompletableFuture<Void> keepAliveUserDataStream(ListenKey listenKey);

  /**
   * Close out a new user data stream.
   *
   */
  CompletableFuture<Void> closeUserDataStream(ListenKey listenKey);
}