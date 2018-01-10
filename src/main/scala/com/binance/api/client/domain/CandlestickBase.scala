package com.binance.api.client.domain

trait CandlestickBase {
  def openTime:                 Instant
  def open:                     Price
  def high:                     Price
  def low:                      Price
  def close:                    Price
  def volume:                   Volume
  def closeTime:                Instant
  def quoteAssetVolume:         String
  def numberOfTrades:           Long
  def takerBuyBaseAssetVolume:  String
  def takerBuyQuoteAssetVolume: String
}
