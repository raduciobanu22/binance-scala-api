package com.binance.api.client.domain

trait CandlestickBase {
  def openTime:                 Long
  def open:                     String
  def high:                     String
  def low:                      String
  def close:                    String
  def volume:                   String
  def closeTime:                Long
  def quoteAssetVolume:         String
  def numberOfTrades:           Long
  def takerBuyBaseAssetVolume:  String
  def takerBuyQuoteAssetVolume: String
}
