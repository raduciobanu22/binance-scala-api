package com.binance.api.client.domain

import io.circe._

object DomainJson {
  def enumDecoder[E <: java.lang.Enum[E]](values: Seq[E]): Decoder[E] =
    Decoder.decodeString.flatMap { str => (c: HCursor) =>
      values
        .find(_.name() == str)
        .toRight(DecodingFailure(s"$str not among $values", c.history))
    }

  implicit lazy val OrderSideDecoder: Decoder[OrderSide] =
    enumDecoder(OrderSide.values())

  implicit lazy val OrderTypeDecoder: Decoder[OrderType] =
    enumDecoder(OrderType.values())

  implicit lazy val TimeInForceDecoder: Decoder[TimeInForce] =
    enumDecoder(TimeInForce.values())

  implicit lazy val ExecutionTypeDecoder: Decoder[ExecutionType] =
    enumDecoder(ExecutionType.values())

  implicit lazy val OrderStatusDecoder: Decoder[OrderStatus] =
    enumDecoder(OrderStatus.values())

  implicit lazy val OrderRejectReasonDecoder: Decoder[OrderRejectReason] =
    enumDecoder(OrderRejectReason.values())

  implicit lazy val AssetBalanceDecoder: Decoder[AssetBalance] =
    Decoder.forProduct3("a", "f", "l")(AssetBalance.apply)

  implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
    Decoder.decodeTuple3[String, String, Seq[String]].map {
      case (a0, a1, _) => OrderBookEntry(a0, a1)
    }

}
