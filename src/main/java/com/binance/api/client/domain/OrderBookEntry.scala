package com.binance.api.client.domain

/**
  * An order book entry consisting of price and quantity.
  */
case class OrderBookEntry(
    price: String,
    qty: String
)
