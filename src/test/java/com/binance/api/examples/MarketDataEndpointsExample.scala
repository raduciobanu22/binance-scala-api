package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.Symbol
import com.binance.api.client.domain.market.CandlestickInterval.WEEKLY
import com.binance.api.client.exception.BinanceApiExceptionError

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Examples on how to get market data information such as the latest price of a symbol, etc.
  */
object MarketDataEndpointsExample extends App {

  val factory = new BinanceApiClientFactory("", "")
  val client  = factory.newAsyncRestClient
  val symbol  = Symbol("NEOETH")

  // Getting depth of a symbol
  client.getOrderBook(symbol, 10).foreach((orderBook) => println(orderBook.asks))
  // Getting latest price of a symbol
  client.get24HrPriceStatistics(symbol).foreach(println)
  // Getting all latest prices
  client.getAllPrices.foreach(println)
  // Getting agg trades
  client.getAggTrades(symbol, limit = Some(10)).foreach(println)
  // Weekly candlestick bars for a symbol
  client.getCandlestickBars(symbol, WEEKLY).foreach(println)
  // Getting all book tickers
  client.getBookTickers.foreach(println)
  // Exception handling
  client.getOrderBook(Symbol("UNKNOWN"), 10).failed.foreach {
    case e: BinanceApiExceptionError =>
      println(e.error.code) // -1121
      println(e.error.msg)  // Invalid symbol
    case th => th.printStackTrace()
  }

}
