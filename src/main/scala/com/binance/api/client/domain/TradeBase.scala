package com.binance.api.client.domain

trait TradeBase {
  def tradeId:                Long
  def price:                  Price
  def quantity:               Quantity
  def tradeTime:              Option[Instant]
  def isBuyerMaker:           Boolean
  def isBestMatch:            Boolean
}
