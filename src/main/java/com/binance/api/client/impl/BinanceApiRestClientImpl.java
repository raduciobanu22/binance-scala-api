package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.*;

import java.util.List;
import java.util.Optional;

import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.createService;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;
import static java.lang.System.currentTimeMillis;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class BinanceApiRestClientImpl implements BinanceApiRestClient {

  public final BinanceApiService binanceApiService;

  public BinanceApiRestClientImpl(String apiKey, String secret) {
    binanceApiService = createService(BinanceApiService.class, apiKey, secret);
  }

  // General endpoints

  @Override
  public void ping() {
    executeSync(binanceApiService.ping());
  }

  @Override
  public Long getServerTime() {
    return executeSync(binanceApiService.getServerTime()).serverTime;
  }

  @Override
  public ExchangeInfo getExchangeInfo() {
    return executeSync(binanceApiService.getExchangeInfo());
  }

  // Market Data endpoints

  @Override
  public OrderBook getOrderBook(String symbol, Integer limit) {
    return executeSync(binanceApiService.getOrderBook(symbol, limit));
  }

  @Override
  public List<AggTrade> getAggTrades(String symbol, Optional<String> fromId, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime) {
    return executeSync(binanceApiService.getAggTrades(symbol, fromId.orElse(null), limit.orElse(null), startTime.orElse(null), endTime.orElse(null)));
  }

  @Override
  public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime) {
    return executeSync(binanceApiService.getCandlestickBars(symbol, interval.intervalId, limit.orElse(null), startTime.orElse(null), endTime.orElse(null)));
  }

  @Override
  public TickerStatistics get24HrPriceStatistics(String symbol) {
    return executeSync(binanceApiService.get24HrPriceStatistics(symbol));
  }

  @Override
  public List<TickerPrice> getAllPrices() {
    return executeSync(binanceApiService.getLatestPrices());
  }

  @Override
  public List<BookTicker> getBookTickers() {
    return executeSync(binanceApiService.getBookTickers());
  }

  @Override
  public NewOrderResponse newOrder(NewOrder order) {
    return executeSync(binanceApiService.newOrder(order.symbol, order.side, order.type,
        order.timeInForce, order.quantity, order.price.orElse(null), order.stopPrice.orElse(null), order.icebergQty.orElse(null),
        order.recvWindow, order.timestamp));
  }

  @Override
  public void newOrderTest(NewOrder order) {
    executeSync(binanceApiService.newOrderTest(order.symbol, order.side, order.type,
        order.timeInForce, order.quantity, order.price.orElse(null), order.stopPrice.orElse(null), order.icebergQty.orElse(null),
        order.recvWindow, order.timestamp));
  }

  // Account endpoints

  @Override
  public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
    return executeSync(binanceApiService.orderStatus(orderStatusRequest.symbol,
        orderStatusRequest.orderId.orElse(null), orderStatusRequest.origClientOrderId.orElse(null),
        orderStatusRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), orderStatusRequest.timestamp.orElse(currentTimeMillis())));
  }

  @Override
  public void cancelOrder(CancelOrderRequest cancelOrderRequest) {
    executeSync(binanceApiService.cancelOrder(
        cancelOrderRequest.symbol,
        cancelOrderRequest.orderId.orElse(null),
        cancelOrderRequest.origClientOrderId.orElse(null),
        cancelOrderRequest.newClientOrderId.orElse(null),
        cancelOrderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        cancelOrderRequest.timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public List<Order> getOpenOrders(OrderRequest orderRequest) {
    return executeSync(binanceApiService.openOrders(orderRequest.symbol, orderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), orderRequest.timestamp.orElse(currentTimeMillis())));
  }

  @Override
  public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
    return executeSync(binanceApiService.allOrders(orderRequest.symbol,
        orderRequest.orderId.orElse(null), orderRequest.limit.orElse(null),
        orderRequest.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), orderRequest.timestamp.orElse(currentTimeMillis())));
  }

  @Override
  public Account getAccount(Optional<Long> recvWindow, Optional<Long> timestamp) {
    return executeSync(binanceApiService.account(recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), timestamp.orElse(currentTimeMillis())));
  }

  @Override
  public List<Trade> getMyTrades(String symbol, Optional<Integer> limit, Optional<Long> fromId, Optional<Long> recvWindow, Optional<Long> timestamp) {
    return executeSync(binanceApiService.getMyTrades(symbol, limit.orElse(null), fromId.orElse(null), recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), timestamp.orElse(currentTimeMillis())));
  }

  @Override
  public void withdraw(String asset, String address, String amount, Optional<String> name) {
    executeSync(binanceApiService.withdraw(asset, address, amount, name.orElse(null), DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  @Override
  public DepositHistory getDepositHistory(String asset) {
    return executeSync(binanceApiService.depositHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  @Override
  public WithdrawHistory getWithdrawHistory(String asset) {
    return executeSync(binanceApiService.withdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  @Override
  public DepositAddress getDepositAddress(String asset) {
    return executeSync(binanceApiService.depositAddress(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  // User stream endpoints

  @Override
  public String startUserDataStream() {
    return executeSync(binanceApiService.startUserDataStream()).toString();
  }

  @Override
  public void keepAliveUserDataStream(String listenKey) {
    executeSync(binanceApiService.keepAliveUserDataStream(listenKey));
  }

  @Override
  public void closeUserDataStream(String listenKey) {
    executeSync(binanceApiService.closeAliveUserDataStream(listenKey));
  }
}
