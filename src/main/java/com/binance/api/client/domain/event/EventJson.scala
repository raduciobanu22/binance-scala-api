package com.binance.api.client.domain.event

import com.binance.api.client.domain.DomainJson._

object EventJson {
  import io.circe._

  implicit lazy val AccountUpdateEventDecoder: Decoder[AccountUpdateEvent] =
    Decoder.forProduct3("e", "E", "B")(AccountUpdateEvent.apply)

  implicit lazy val CandlestickDetailedDecoder: Decoder[CandlestickDetailed] =
    Decoder.forProduct15("t",
                         "o",
                         "h",
                         "l",
                         "c",
                         "v",
                         "T",
                         "i",
                         "f",
                         "L",
                         "q",
                         "n",
                         "V",
                         "Q",
                         "x")(CandlestickDetailed.apply)

  implicit lazy val CandlestickEventDecoder: Decoder[CandlestickEvent] =
    Decoder.forProduct4("e", "E", "s", "k")(CandlestickEvent.apply)

  implicit lazy val OrderTradeUpdateEventDecoder: Decoder[OrderTradeUpdateEvent] =
    Decoder.forProduct20("e",
                         "E",
                         "s",
                         "c",
                         "S",
                         "o",
                         "f",
                         "q",
                         "p",
                         "x",
                         "X",
                         "r",
                         "i",
                         "l",
                         "z",
                         "L",
                         "n",
                         "N",
                         "T",
                         "t")(OrderTradeUpdateEvent.apply)

  implicit lazy val UserDataUpdateEventDecoder
    : Decoder[Either[OrderTradeUpdateEvent, AccountUpdateEvent]] =
    Decoder.decodeJsonObject.flatMap { (obj: JsonObject) =>
      obj("e").flatMap(_.asString) match {
        case Some("executionReport") =>
          OrderTradeUpdateEventDecoder.map(Left.apply)
        case Some("outboundAccountInfo") =>
          AccountUpdateEventDecoder.map(Right.apply)
        case _ =>
          (c: HCursor) =>
            Left(DecodingFailure("Not a valid UserDataUpdateEvent", c.history))
      }
    }

  implicit lazy val AggTradeEventDecoder: Decoder[AggTradeEvent] =
    Decoder.forProduct11("e", "E", "s", "a", "p", "q", "f", "l", "T", "m", "M")(
      AggTradeEvent.apply)

  implicit lazy val DepthEventDecoder: Decoder[DepthEvent] =
    Decoder.forProduct6("e", "E", "s", "u", "b", "a")(DepthEvent.apply)

  implicit lazy val ListenKeyDecoder: Decoder[ListenKey] =
    Decoder.forProduct1("listenKey")(ListenKey.apply)

}
