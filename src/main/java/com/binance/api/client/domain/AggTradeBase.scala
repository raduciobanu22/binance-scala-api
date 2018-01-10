package com.binance.api.client.domain

trait AggTradeBase {
  def aggregatedTradeId:      Long
  def price:                  Price
  def quantity:               Quantity
  def firstBreakdownTradeId:  Long
  def lastBreakdownTradeId:   Long
  def tradeTime:              Option[Instant]
  def isBuyerMaker:           Boolean
  def wasTradeBestPriceMatch: Boolean
}
