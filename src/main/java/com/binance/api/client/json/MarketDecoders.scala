package com.binance.api.client.json

import com.binance.api.client.domain.{Instant, Price, Volume}
import com.binance.api.client.domain.market._
import io.circe._

trait MarketDecoders extends DomainDecoders {

  implicit lazy val AggTradeDecoder: Decoder[AggTrade] =
    Decoder.forProduct8("a", "p", "q", "f", "l", "T", "m", "M")(AggTrade.apply)

  implicit lazy val BookTickerDecoder: Decoder[BookTicker] =
    Decoder.forProduct5("symbol", "bidPrice", "bidQty", "askPrice", "askQty")(BookTicker.apply)

  implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] =
    JavaEnumDecoder(CandlestickInterval.values())

  implicit lazy val CandlestickDecoder: Decoder[Candlestick] =
    Decoder
      .decodeTuple12[Instant, Price, Price, Price, Price, Volume, Instant, String, Long, String, String, String]
      .map {
        case (a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, _) =>
          Candlestick(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)
      }

  implicit lazy val OrderBookDecoder: Decoder[OrderBook] =
    Decoder.forProduct3("lastUpdateId", "bids", "asks")(OrderBook.apply)

  implicit lazy val TickerPriceDecoder: Decoder[TickerPrice] =
    Decoder.forProduct2("symbol", "price")(TickerPrice.apply)

  implicit lazy val TickerStatisticsDecoder: Decoder[TickerStatistics] =
    Decoder.forProduct16(
      "priceChange",
      "priceChangePercent",
      "weightedAvgPrice",
      "prevClosePrice",
      "lastPrice",
      "bidPrice",
      "askPrice",
      "openPrice",
      "highPrice",
      "lowPrice",
      "volume",
      "openTime",
      "closeTime",
      "firstId",
      "lastId",
      "count"
    )(TickerStatistics.apply)

}
