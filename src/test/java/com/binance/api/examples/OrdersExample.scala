package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.TimeInForce
import com.binance.api.client.domain.account.NewOrder.{limitBuy, marketBuy}
import com.binance.api.client.domain.account.request._
import com.binance.api.client.exception.BinanceApiExceptionError

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Examples on how to place orders, cancel them, and query account information.
  */
object OrdersExample extends App {

  val factory = new BinanceApiClientFactory("YOUR_API_KEY", "YOUR_SECRET")
  val client = factory.newAsyncRestClient

  // Getting list of open orders
  client.getOpenOrders(OrderRequest("LINKETH")).foreach(println)
  // Getting list of all orders with a limit of 10
  client.getAllOrders(AllOrdersRequest("LINKETH", limit = Some(10))).foreach(println)
  // Get status of a particular order
  client.getOrderStatus(OrderStatusRequest("LINKETH", orderId = Some(751698L))).foreach(println)

  // Canceling an order
  client.cancelOrder(CancelOrderRequest("LINKETH", orderId = Some(756762l))).failed.foreach {
    case e: BinanceApiExceptionError => println(e)
    case other => other.printStackTrace()
  }

  // Placing a test LIMIT order
  client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"))
  // Placing a test MARKET order
  client.newOrderTest(marketBuy("LINKETH", "1000"))
  // Placing a real LIMIT order
  client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001")).foreach(println)
}
