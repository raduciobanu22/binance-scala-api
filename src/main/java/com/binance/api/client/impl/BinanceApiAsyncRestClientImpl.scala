package com.binance.api.client.impl

import java.lang.System.currentTimeMillis

import com.binance.api.client.BinanceApiAsyncRestClient
import com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW
import com.binance.api.client.domain.account.AccountJson._
import com.binance.api.client.domain.account._
import com.binance.api.client.domain.account.request.{AllOrdersRequest, CancelOrderRequest, OrderRequest, OrderStatusRequest}
import com.binance.api.client.domain.event.EventJson._
import com.binance.api.client.domain.event.ListenKey
import com.binance.api.client.domain.general.GeneralJson._
import com.binance.api.client.domain.general.{ExchangeInfo, ServerTime}
import com.binance.api.client.domain.market.MarketJson._
import com.binance.api.client.domain.market._

import scala.concurrent.{ExecutionContext, Future}

private object BinanceApiAsyncRestClientImpl {
  def longHack(ol: Option[Long], orElse: java.lang.Long): java.lang.Long =
    ol match {
      case Some(l) => l
      case None    => orElse
    }

  def intHack(ol: Option[Int], orElse: java.lang.Integer): java.lang.Integer =
    ol match {
      case Some(l) => l
      case None    => orElse
    }
}

/**
  * Implementation of Binance's REST API using Retrofit with asynchronous/non-blocking method calls.
  */
class BinanceApiAsyncRestClientImpl(service: BinanceApiService)(implicit ex: ExecutionContext)
    extends BinanceApiAsyncRestClient {
  import BinanceApiAsyncRestClientImpl.{intHack, longHack}

  override def ping: Future[Unit] =
    RunRequest[Unit](service.ping)

  override def getServerTime: Future[ServerTime] =
    RunRequest[ServerTime](service.getServerTime)

  override def getExchangeInfo: Future[ExchangeInfo] =
    RunRequest[ExchangeInfo](service.getExchangeInfo)

  override def getOrderBook(symbol: String, limit: Int): Future[OrderBook] =
    RunRequest[OrderBook](service.getOrderBook(symbol, limit))

  def getAggTrades(symbol:    String,
                   fromId:    Option[String] = None,
                   limit:     Option[Int] = None,
                   startTime: Option[Long] = None,
                   endTime:   Option[Long] = None): Future[List[AggTrade]] =
    RunRequest[List[AggTrade]](
      service
        .getAggTrades(symbol, fromId.orNull, intHack(limit, null), longHack(startTime, null), longHack(endTime, null))
    )

  def getCandlestickBars(symbol:    String,
                         interval:  CandlestickInterval,
                         limit:     Option[Int] = None,
                         startTime: Option[Long] = None,
                         endTime:   Option[Long] = None): Future[List[Candlestick]] =
    RunRequest[List[Candlestick]](
      service
        .getCandlestickBars(symbol,
                            interval.getIntervalId,
                            intHack(limit, null),
                            longHack(startTime, null),
                            longHack(endTime, null))
    )

  override def get24HrPriceStatistics(symbol: String): Future[TickerStatistics] =
    RunRequest[TickerStatistics](service.get24HrPriceStatistics(symbol))

  override def getAllPrices: Future[List[TickerPrice]] =
    RunRequest[List[TickerPrice]](service.getLatestPrices)

  override def getBookTickers: Future[List[BookTicker]] =
    RunRequest[List[BookTicker]](service.getBookTickers)

  override def newOrder(order: NewOrder): Future[NewOrderResponse] =
    RunRequest[NewOrderResponse](
      service.newOrder(
        order.symbol,
        order.side,
        order.`type`,
        order.timeInForce.orNull,
        order.quantity,
        order.price.orNull,
        order.stopPrice.orNull,
        order.icebergQty.orNull,
        longHack(order.recvWindow, null),
        longHack(order.timestamp, null)
      )
    )

  override def newOrderTest(in: NewOrder): Future[Unit] =
    RunRequest[Unit](
      service.newOrderTest(
        in.symbol,
        in.side,
        in.`type`,
        in.timeInForce.orNull,
        in.quantity,
        in.price.orNull,
        in.stopPrice.orNull,
        in.icebergQty.orNull,
        longHack(in.recvWindow, null),
        longHack(in.timestamp, null)
      )
    )

  override def getOrderStatus(in: OrderStatusRequest): Future[Order] =
    RunRequest[Order](
      service.orderStatus(
        in.symbol,
        longHack(in.orderId, null),
        in.origClientOrderId.orNull,
        longHack(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
        longHack(in.timestamp, currentTimeMillis)
      )
    )

  override def cancelOrder(in: CancelOrderRequest): Future[Unit] =
    RunRequest[Unit](
      service.cancelOrder(
        in.symbol,
        longHack(in.orderId, null),
        in.origClientOrderId.orNull,
        in.newClientOrderId.orNull,
        longHack(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
        longHack(in.timestamp, currentTimeMillis)
      )
    )

  override def getOpenOrders(in: OrderRequest): Future[List[Order]] =
    RunRequest[List[Order]](
      service.openOrders(in.symbol,
                                   longHack(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
                                   longHack(in.timestamp, currentTimeMillis))
    )

  override def getAllOrders(in: AllOrdersRequest): Future[List[Order]] =
    RunRequest[List[Order]](
      service.allOrders(in.symbol,
                                  longHack(in.orderId, null),
                                  in.limit.orNull,
                                  longHack(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
                                  longHack(in.timestamp, currentTimeMillis))
    )

  def getAccount(recvWindow: Option[Long], timestamp: Option[Long]): Future[Account] =
    RunRequest[Account](
      service.account(longHack(recvWindow, DEFAULT_RECEIVING_WINDOW), longHack(timestamp, currentTimeMillis))
    )

  def getMyTrades(symbol:     String,
                  limit:      Option[Int],
                  fromId:     Option[Long],
                  recvWindow: Option[Long],
                  timestamp:  Option[Long]): Future[List[Trade]] =
    RunRequest[List[Trade]](
      service.getMyTrades(symbol,
                                    intHack(limit, null),
                                    longHack(fromId, null),
                                    longHack(recvWindow, DEFAULT_RECEIVING_WINDOW),
                                    longHack(timestamp, currentTimeMillis))
    )

  override def withdraw(asset: String, address: String, amount: String, name: Option[String] = None): Future[Unit] =
    RunRequest[Unit](
      service.withdraw(asset, address, amount, name.orNull, DEFAULT_RECEIVING_WINDOW, currentTimeMillis)
    )

  override def getDepositHistory(asset: String): Future[DepositHistory] =
    RunRequest[DepositHistory](
      service
        .depositHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis)
    )

  override def getWithdrawHistory(asset: String): Future[WithdrawHistory] =
    RunRequest[WithdrawHistory](
      service
        .withdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis)
    )

  override def getDepositAddress(asset: String): Future[DepositAddress] =
    RunRequest[DepositAddress](
      service
        .depositAddress(asset, DEFAULT_RECEIVING_WINDOW, currentTimeMillis)
    )

  override def startUserDataStream: Future[ListenKey] =
    RunRequest[ListenKey](service.startUserDataStream)

  override def keepAliveUserDataStream(listenKey: ListenKey): Future[Unit] =
    RunRequest[Unit](service.keepAliveUserDataStream(listenKey.listenKey))

  override def closeUserDataStream(listenKey: ListenKey): Future[Unit] =
    RunRequest[Unit](service.closeAliveUserDataStream(listenKey.listenKey))
}
