package com.binance.api.client

import com.binance.api.client.domain.account._
import com.binance.api.client.domain.account.request.{AllOrdersRequest, CancelOrderRequest, OrderRequest, OrderStatusRequest}
import com.binance.api.client.domain.event.ListenKey
import com.binance.api.client.domain.general.{ExchangeInfo, ServerTime}
import com.binance.api.client.domain.market._

import scala.concurrent.Future

/**
  * Binance API façade, supporting asynchronous/non-blocking access Binance's REST API.
  */
trait BinanceApiAsyncRestClient {

  /**
    * Test connectivity to the Rest API.
    */
  def ping: Future[Unit]

  /**
    * Check server time.
    */
  def getServerTime: Future[ServerTime]

  /**
    * Current exchange trading rules and symbol information
    */
  def getExchangeInfo: Future[ExchangeInfo]

  /**
    * Get order book of a symbol (asynchronous)
    *
    * @param symbol ticker symbol (e.g. ETHBTC)
    */
  def getOrderBook(symbol: String, limit: Int): Future[OrderBook]

  /**
    * Get compressed, aggregate trades. Trades that fill at the time, from the same order, with
    * the same price will have the quantity aggregated.
    *
    * If both <code>startTime</code> and <code>endTime</code> are sent, <code>limit</code>should not
    * be sent AND the distance between <code>startTime</code> and <code>endTime</code> must be less than 24 hours.
    *
    * @param symbol    symbol to aggregate (mandatory)
    * @param fromId    ID to get aggregate trades from INCLUSIVE (Option)
    * @param limit     Default 500; max 500 (Option)
    * @param startTime Timestamp in ms to get aggregate trades from INCLUSIVE (Option).
    * @return a list of aggregate trades for the given symbol
    */
  def getAggTrades(
      symbol: String,
      fromId: Option[String] = None,
      limit: Option[Int] = None,
      startTime: Option[Long] = None,
      endTime: Option[Long] = None): Future[List[AggTrade]]

  /**
    *
    * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
    *
    * @param symbol    symbol to aggregate (mandatory)
    * @param interval  candlestick interval (mandatory)
    * @param limit     Default 500; max 500 (Option)
    * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (Option).
    */
  def getCandlestickBars(
      symbol: String,
      interval: CandlestickInterval,
      limit: Option[Int] = None,
      startTime: Option[Long] = None,
      endTime: Option[Long] = None): Future[List[Candlestick]]

  /**
    * Get 24 hour price change statistics (asynchronous).
    *
    */
  def get24HrPriceStatistics(
      symbol: String): Future[TickerStatistics]

  /**
    * Get Latest price for all symbols (asynchronous).
    */
  def getAllPrices: Future[List[TickerPrice]]

  /**
    * Get best price/qty on the order book for all symbols (asynchronous).
    */
  def getBookTickers: Future[List[BookTicker]]

  /**
    * Send in a new order (asynchronous)
    *
    */
  def newOrder(order: NewOrder): Future[NewOrderResponse]

  /**
    * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
    *
    */
  def newOrderTest(order: NewOrder): Future[Unit]

  /**
    * Check an order's status (asynchronous).
    *
    */
  def getOrderStatus(
      orderStatusRequest: OrderStatusRequest): Future[Order]

  /**
    * Cancel an active order (asynchronous).
    *
    */
  def cancelOrder(
      cancelOrderRequest: CancelOrderRequest): Future[Unit]

  /**
    * Get all open orders on a symbol (asynchronous).
    *
    */
  def getOpenOrders(
      orderRequest: OrderRequest): Future[List[Order]]

  /**
    * Get all account orders; active, canceled, or filled.
    *
    */
  def getAllOrders(
      orderRequest: AllOrdersRequest): Future[List[Order]]

  /**
    * Get current account information (async).
    */
  def getAccount(recvWindow: Option[Long] = None,
                 timestamp: Option[Long] = None): Future[Account]

  /**
    * Get trades for a specific account and symbol.
    *
    * @param symbol symbol to get trades from
    * @param limit default 500; max 500
    */
  def getMyTrades(
      symbol: String,
      limit: Option[Int] = None,
      fromId: Option[Long] = None,
      recvWindow: Option[Long] = None,
      timestamp: Option[Long] = None): Future[List[Trade]]

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
  def withdraw(asset: String,
               address: String,
               amount: String,
               name: Option[String]): Future[Unit]

  /**
    * Fetch account deposit history.
    */
  def getDepositHistory(asset: String): Future[DepositHistory]

  /**
    * Fetch account withdraw history.
    */
  def getWithdrawHistory(asset: String): Future[WithdrawHistory]

  /**
    * Fetch deposit address.
    */
  def getDepositAddress(asset: String): Future[DepositAddress]

  /**
    * Start a new user data stream.
    */
  def startUserDataStream: Future[ListenKey]

  /**
    * PING a user data stream to prevent a time out.
    *
    */
  def keepAliveUserDataStream(listenKey: ListenKey): Future[Unit]

  /**
    * Close out a new user data stream.
    *
    */
  def closeUserDataStream(listenKey: ListenKey): Future[Unit]
}
