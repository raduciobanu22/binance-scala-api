package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain._
import com.binance.api.client.domain.account.NewOrder.{limitBuy, marketBuy}
import com.binance.api.client.domain.account.request._
import com.binance.api.client.exception.BinanceApiExceptionError

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Examples on how to place orders, cancel them, and query account information.
  */
object OrdersExample extends App {

  val factory = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client  = factory.newAsyncRestClient
  val symbol  = Symbol("LINKETH")

  // Getting list of open orders
  client.getOpenOrders(OrderRequest(symbol)).foreach(println)
  // Getting list of all orders with a limit of 10
  client.getAllOrders(AllOrdersRequest(symbol, limit = Some(10))).foreach(println)
  // Get status of a particular order
  client.getOrderStatus(OrderStatusRequest(symbol, orderId = Some(OrderId(751698L)))).foreach(println)

  // Canceling an order
  client.cancelOrder(CancelOrderRequest(symbol, orderId = Some(OrderId(756762L)))).failed.foreach {
    case e: BinanceApiExceptionError => println(e)
    case other => other.printStackTrace()
  }

  // Placing a test LIMIT order
  client.newOrderTest(limitBuy(symbol, TimeInForce.GTC, Quantity(BigDecimal("1000")), Price(BigDecimal("0.0001")))).foreach(println)
  // Placing a test MARKET order
  client.newOrderTest(marketBuy(symbol, Quantity(BigDecimal("1000")))).foreach(println)
//   Placing a real LIMIT order
  client.newOrder(limitBuy(symbol, TimeInForce.GTC, Quantity(BigDecimal("1000")), Price(BigDecimal("0.0001")))).foreach(println)
}
