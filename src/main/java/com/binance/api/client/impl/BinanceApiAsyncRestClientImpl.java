package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.ServerTime;
import com.binance.api.client.domain.market.*;
import com.binance.api.client.exception.BinanceApiException;
import retrofit2.Call;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

  static <T> CompletableFuture<T> futureGlue(Call<T> call) {
    CompletableFuture<T> ret = new CompletableFuture<>();

    try {
      call.enqueue(new BinanceApiCallbackAdapter<T>(ret::complete) {
        @Override
        public void onFailure(Call call, Throwable throwable) {
          ret.completeExceptionally(throwable);
        }
      });
    } catch (BinanceApiException throwable) {
      ret.completeExceptionally(throwable);
    }
    return ret;
  }

  // General endpoints

  @Override
  public CompletableFuture<Void> ping() {
    return futureGlue(binanceApiService.ping());
  }

  @Override
  public CompletableFuture<ServerTime> getServerTime() {
    return futureGlue(binanceApiService.getServerTime());
  }

  @Override
  public CompletableFuture<ExchangeInfo> getExchangeInfo() {
    return futureGlue(binanceApiService.getExchangeInfo());
  }

  // Market Data endpoints

  @Override
  public CompletableFuture<OrderBook> getOrderBook(String symbol, Integer limit) {
    return futureGlue(binanceApiService.getOrderBook(symbol, limit));
  }

  @Override
  public CompletableFuture<List<AggTrade>> getAggTrades(String symbol, Optional<String> fromId, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime) {
    return futureGlue(binanceApiService.getAggTrades(
        symbol,
        fromId.orElse(null),
        limit.orElse(null),
        startTime.orElse(null),
        endTime.orElse(null)
    ));
  }

  @Override
  public CompletableFuture<List<Candlestick>> getCandlestickBars(String symbol, CandlestickInterval interval, Optional<Integer> limit, Optional<Long> startTime, Optional<Long> endTime) {
    return futureGlue(binanceApiService.getCandlestickBars(
        symbol,
        interval.getIntervalId(),
        limit.orElse(null),
        startTime.orElse(null),
        endTime.orElse(null)
    ));
  }

  @Override
  public CompletableFuture<TickerStatistics> get24HrPriceStatistics(String symbol) {
    return futureGlue(binanceApiService.get24HrPriceStatistics(symbol));
  }

  @Override
  public CompletableFuture<List<TickerPrice>> getAllPrices() {
    return futureGlue(binanceApiService.getLatestPrices());
  }

  @Override
  public CompletableFuture<List<BookTicker>> getBookTickers() {
    return futureGlue(binanceApiService.getBookTickers());
  }

  @Override
  public CompletableFuture<NewOrderResponse> newOrder(NewOrder order) {
    return futureGlue(binanceApiService.newOrder(
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
    ));
  }

  @Override
  public CompletableFuture<Void> newOrderTest(NewOrder in) {
    return futureGlue(binanceApiService.newOrderTest(
        in.symbol,
        in.side,
        in.type,
        in.timeInForce,
        in.quantity,
        in.price.orElse(null),
        in.stopPrice.orElse(null),
        in.icebergQty.orElse(null),
        in.recvWindow,
        in.timestamp
    ));
  }

  // Account endpoints

  @Override
  public CompletableFuture<Order> getOrderStatus(OrderStatusRequest in) {
    return futureGlue(binanceApiService.orderStatus(
        in.symbol,
        in.orderId.orElse(null),
        in.origClientOrderId.orElse(null),
        in.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        in.timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<Void> cancelOrder(CancelOrderRequest in) {
    return futureGlue(binanceApiService.cancelOrder(
        in.symbol,
        in.orderId.orElse(null),
        in.origClientOrderId.orElse(null),
        in.newClientOrderId.orElse(null),
        in.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        in.timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<List<Order>> getOpenOrders(OrderRequest in) {
    return futureGlue(binanceApiService.openOrders(
        in.symbol,
        in.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        in.timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<List<Order>> getAllOrders(AllOrdersRequest in) {
    return futureGlue(binanceApiService.allOrders(
        in.symbol,
        in.orderId.orElse(null),
        in.limit.orElse(null),
        in.recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        in.timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<Account> getAccount(Optional<Long> recvWindow, Optional<Long> timestamp) {
    return futureGlue(binanceApiService.account(
        recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<List<Trade>> getMyTrades(String symbol, Optional<Integer> limit, Optional<Long> fromId, Optional<Long> recvWindow, Optional<Long> timestamp) {
    return futureGlue(
    binanceApiService.getMyTrades(
        symbol,
        limit.orElse(null),
        fromId.orElse(null),
        recvWindow.orElse(DEFAULT_RECEIVING_WINDOW),
        timestamp.orElse(currentTimeMillis())
    ));
  }

  @Override
  public CompletableFuture<Void> withdraw(String asset, String address, String amount, Optional<String> name) {
    return futureGlue(binanceApiService.withdraw(asset, address, amount, name.orElse(null), DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        );
  }

  @Override
  public CompletableFuture<DepositHistory> getDepositHistory(String asset) {
    return futureGlue(binanceApiService.depositHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        );
  }

  @Override
  public CompletableFuture<WithdrawHistory> getWithdrawHistory(String asset) {
    return futureGlue(binanceApiService.withdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        );
  }

  @Override
  public CompletableFuture<DepositAddress> getDepositAddress(String asset) {
    return futureGlue(binanceApiService.depositAddress(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis())
        );
  }

  // User stream endpoints

  @Override
  public CompletableFuture<ListenKey> startUserDataStream() {
    return futureGlue(binanceApiService.startUserDataStream());
  }

  @Override
  public CompletableFuture<Void> keepAliveUserDataStream(ListenKey listenKey) {
    return futureGlue(binanceApiService.keepAliveUserDataStream(listenKey.listenKey));
  }

  @Override
  public CompletableFuture<Void> closeUserDataStream(ListenKey listenKey) {
    return futureGlue(binanceApiService.closeAliveUserDataStream(listenKey.listenKey));
  }
}