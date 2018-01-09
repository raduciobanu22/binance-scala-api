package com.binance.api.client.domain

trait AggTradeBase {
  def aggregatedTradeId:      Long
  def price:                  String
  def quantity:               String
  def firstBreakdownTradeId:  Long
  def lastBreakdownTradeId:   Long
  def tradeTime:              Option[Long]
  def isBuyerMaker:           Boolean
  def wasTradeBestPriceMatch: Boolean
}
