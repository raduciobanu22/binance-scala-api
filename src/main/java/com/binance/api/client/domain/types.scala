package com.binance.api.client.domain

import java.util.Date

case class Instant(millis: Long) {
  def str: String = new Date(millis).toString
}
case class Quantity(value:  BigDecimal)
case class Volume(value:    BigDecimal)
case class Price(value:     BigDecimal)
case class Amount(value:    BigDecimal)
case class Percent(value:   BigDecimal)
case class PriceDiff(value: BigDecimal)

case class Asset(value:   String)
case class Symbol(value:  String)
case class OrderId(value: Long)
