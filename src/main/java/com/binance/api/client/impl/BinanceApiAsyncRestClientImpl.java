package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiCallback;
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

import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.createService;
import static java.lang.System.currentTimeMillis;

/**
 * Implementation of Binance's REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class BinanceApiAsyncRestClientImpl implements BinanceApiAsyncRestClient {

  public final BinanceApiService binanceApiService;

  public BinanceApiAsyncRestClientImpl(String apiKey, String secret) {
    binanceApiService = createService(BinanceApiService.class, apiKey, secret);
  }

  // General endpoints

  @Override
  public void ping(BinanceApiCallback<Void> callback) {
    binanceApiService.ping().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getServerTime(BinanceApiCallback<ServerTime> callback) {
    binanceApiService.getServerTime().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getExchangeInfo(BinanceApiCallback<ExchangeInfo> callback) {
    binanceApiService.getExchangeInfo().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  // Market Data endpoints

  @Override
  public void getOrderBook(String symbol, Integer limit, BinanceApiCallback<OrderBook> callback) {
    binanceApiService.getOrderBook(symbol, limit).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getAggTrades(String symbol, Optional<String> fromId, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime, BinanceApiCallback<List<AggTrade>> callback) {
    binanceApiService.getAggTrades(
        symbol,
        fromId.orElse(null),
        limit.orElse(null),
        startTime.orElse(null),
        endTime.orElse(null)
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getCandlestickBars(String symbol, CandlestickInterval interval, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime, BinanceApiCallback<List<Candlestick>> callback) {
    binanceApiService.getCandlestickBars(
        symbol,
        interval.getIntervalId(),
        limit.orElse(null),
        startTime.orElse(null),
        endTime.orElse(null)
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void get24HrPriceStatistics(String symbol, BinanceApiCallback<TickerStatistics> callback) {
    binanceApiService.get24HrPriceStatistics(symbol).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getAllPrices(BinanceApiCallback<List<TickerPrice>> callback) {
    binanceApiService.getLatestPrices().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getBookTickers(BinanceApiCallback<List<BookTicker>> callback) {
    binanceApiService.getBookTickers().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void newOrder(NewOrder order, BinanceApiCallback<NewOrderResponse> callback) {
    binanceApiService.newOrder(
        order.symbol,
        order.side,
        order.type,
        order.timeInForce,
        order.quantity,
        order.price.orElse(null),
        order.stopPrice.orElse(null),
        order.icebergQty.orElse(null),
        order.recvWindow,
        order.timestamp
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void newOrderTest(NewOrder order, BinanceApiCallback<Void> callback) {
    binanceApiService.newOrderTest(
        order.symbol,
        order.side,
        order.type,
        order.timeInForce,
        order.quantity,
        order.price.orElse(null),
        order.stopPrice.orElse(null),
        order.icebergQty.orElse(null),
        order.recvWindow,
        order.timestamp
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  // Account endpoints

  @Override
  public void getOrderStatus(OrderStatusRequest orderStatusRequest, BinanceApiCallback<Order> callback) {
    binanceApiService.orderStatus(
        orderStatusRequest.symbol,
        orderStatusRequest.orderId.orElse(null),
        orderStatusRequest.origClientOrderId.orElse(null),
        orderStatusRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        orderStatusRequest.timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void cancelOrder(CancelOrderRequest cancelOrderRequest, BinanceApiCallback<Void> callback) {
    binanceApiService.cancelOrder(
        cancelOrderRequest.symbol,
        cancelOrderRequest.orderId.orElse(null),
        cancelOrderRequest.origClientOrderId.orElse(null),
        cancelOrderRequest.newClientOrderId.orElse(null),
        cancelOrderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        cancelOrderRequest.timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getOpenOrders(OrderRequest orderRequest, BinanceApiCallback<List<Order>> callback) {
    binanceApiService.openOrders(
        orderRequest.symbol,
        orderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        orderRequest.timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getAllOrders(AllOrdersRequest orderRequest, BinanceApiCallback<List<Order>> callback) {
    binanceApiService.allOrders(
        orderRequest.symbol,
        orderRequest.orderId.orElse(null),
        orderRequest.limit.orElse(null),
        orderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        orderRequest.timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getAccount(Optional<Long> recvWindow, Optional<Long> timestamp, BinanceApiCallback<Account> callback) {
    binanceApiService.account(
        recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getMyTrades(String symbol, Optional<Integer> limit, Optional<Long> fromId, Optional<Long> recvWindow, Optional<Long> timestamp, BinanceApiCallback<List<Trade>> callback) {
    binanceApiService.getMyTrades(
        symbol,
        limit.orElse(null),
        fromId.orElse(null),
        recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        timestamp.orElse(currentTimeMillis())
    ).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void withdraw(String asset, String address, String amount, String name, BinanceApiCallback<Void> callback) {
    binanceApiService.withdraw(asset, address, amount, name, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        .enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getDepositHistory(String asset, BinanceApiCallback<DepositHistory> callback) {
    binanceApiService.depositHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        .enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getWithdrawHistory(String asset, BinanceApiCallback<WithdrawHistory> callback) {
    binanceApiService.withdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        .enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void getDepositAddress(String asset, BinanceApiCallback<DepositAddress> callback) {
    binanceApiService.depositAddress(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        .enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  // User stream endpoints

  @Override
  public void startUserDataStream(BinanceApiCallback<ListenKey> callback) {
    binanceApiService.startUserDataStream().enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void keepAliveUserDataStream(String listenKey, BinanceApiCallback<Void> callback) {
    binanceApiService.keepAliveUserDataStream(listenKey).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }

  @Override
  public void closeUserDataStream(String listenKey, BinanceApiCallback<Void> callback) {
    binanceApiService.closeAliveUserDataStream(listenKey).enqueue(new BinanceApiCallbackAdapter<>(callback));
  }
}