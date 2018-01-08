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

  private final BinanceApiService binanceApiService;

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
    return executeSync(binanceApiService.getServerTime()).getServerTime();
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
    return executeSync(binanceApiService.getCandlestickBars(symbol, interval.getIntervalId(), limit.orElse(null), startTime.orElse(null), endTime.orElse(null)));
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
    return executeSync(binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
        order.getTimeInForce(), order.getQuantity(), order.getPrice().orElse(null), order.getStopPrice().orElse(null), order.getIcebergQty().orElse(null),
        order.getRecvWindow(), order.getTimestamp()));
  }

  @Override
  public void newOrderTest(NewOrder order) {
    executeSync(binanceApiService.newOrderTest(order.getSymbol(), order.getSide(), order.getType(),
        order.getTimeInForce(), order.getQuantity(), order.getPrice().orElse(null), order.getStopPrice().orElse(null), order.getIcebergQty().orElse(null),
        order.getRecvWindow(), order.getTimestamp()));
  }

  // Account endpoints

  @Override
  public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
    return executeSync(binanceApiService.getOrderStatus(orderStatusRequest.getSymbol(),
        orderStatusRequest.getOrderId().orElse(null), orderStatusRequest.getOrigClientOrderId().orElse(null),
        orderStatusRequest.getRecvWindow().orElse(DEFAULT_RECEIVING_WINDOW), orderStatusRequest.getTimestamp().orElse(currentTimeMillis())));
  }

  @Override
  public void cancelOrder(CancelOrderRequest cancelOrderRequest) {
    executeSync(binanceApiService.cancelOrder(cancelOrderRequest.getSymbol(),
        cancelOrderRequest.getOrderId().orElse(null), cancelOrderRequest.getOrigClientOrderId().orElse(null), cancelOrderRequest.getNewClientOrderId().orElse(null),
        cancelOrderRequest.getRecvWindow().orElse(DEFAULT_RECEIVING_WINDOW), cancelOrderRequest.getTimestamp().orElse(currentTimeMillis())));
  }

  @Override
  public List<Order> getOpenOrders(OrderRequest orderRequest) {
    return executeSync(binanceApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow().orElse(DEFAULT_RECEIVING_WINDOW), orderRequest.getTimestamp().orElse(currentTimeMillis())));
  }

  @Override
  public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
    return executeSync(binanceApiService.getAllOrders(orderRequest.getSymbol(),
        orderRequest.getOrderId().orElse(null), orderRequest.getLimit().orElse(null),
        orderRequest.getRecvWindow().orElse(DEFAULT_RECEIVING_WINDOW), orderRequest.getTimestamp().orElse(currentTimeMillis())));
  }

  @Override
  public Account getAccount(Optional<Long> recvWindow, Optional<Long> timestamp) {
    return executeSync(binanceApiService.getAccount(recvWindow.orElse(DEFAULT_RECEIVING_WINDOW), timestamp.orElse(currentTimeMillis())));
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
    return executeSync(binanceApiService.getDepositHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  @Override
  public WithdrawHistory getWithdrawHistory(String asset) {
    return executeSync(binanceApiService.getWithdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  @Override
  public DepositAddress getDepositAddress(String asset) {
    return executeSync(binanceApiService.getDepositAddress(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
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
