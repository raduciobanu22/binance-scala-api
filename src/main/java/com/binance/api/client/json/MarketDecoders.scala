package com.binance.api.client.json

import com.binance.api.client.domain.{Instant, Price, Volume}
import com.binance.api.client.domain.market._
import io.circe._, generic.semiauto.deriveDecoder

trait MarketDecoders extends DomainDecoders {

  implicit lazy val AggTradeDecoder: Decoder[AggTrade] =
    Decoder.forProduct8("a", "p", "q", "f", "l", "T", "m", "M")(AggTrade.apply)

  implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] =
    JavaEnumDecoder(CandlestickInterval.values())

  implicit lazy val CandlestickDecoder: Decoder[Candlestick] =
    Decoder
      .decodeTuple12[Instant, Price, Price, Price, Price, Volume, Instant, String, Long, String, String, String]
      .map {
        case (a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, _) =>
          Candlestick(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)
      }

  implicit lazy val BookTickerDecoder:       Decoder[BookTicker]       = deriveDecoder[BookTicker]
  implicit lazy val OrderBookDecoder:        Decoder[OrderBook]        = deriveDecoder[OrderBook]
  implicit lazy val TickerPriceDecoder:      Decoder[TickerPrice]      = deriveDecoder[TickerPrice]
  implicit lazy val TickerStatisticsDecoder: Decoder[TickerStatistics] = deriveDecoder[TickerStatistics]

}
