package com.binance.api.client.json

import com.binance.api.client.domain._
import io.circe._

trait DomainDecoders {

  implicit lazy val OrderSideDecoder: Decoder[OrderSide] =
    JavaEnumDecoder(OrderSide.values())

  implicit lazy val OrderTypeDecoder: Decoder[OrderType] =
    JavaEnumDecoder(OrderType.values())

  implicit lazy val TimeInForceDecoder: Decoder[TimeInForce] =
    JavaEnumDecoder(TimeInForce.values())

  implicit lazy val ExecutionTypeDecoder: Decoder[ExecutionType] =
    JavaEnumDecoder(ExecutionType.values())

  implicit lazy val OrderStatusDecoder: Decoder[OrderStatus] =
    JavaEnumDecoder(OrderStatus.values())

  implicit lazy val OrderRejectReasonDecoder: Decoder[OrderRejectReason] =
    JavaEnumDecoder(OrderRejectReason.values())

  implicit lazy val AssetBalanceDecoder: Decoder[AssetBalance] =
    Decoder.forProduct3("a", "f", "l")(AssetBalance.apply)

  implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
    Decoder.decodeTuple3[String, String, Seq[String]].map {
      case (a0, a1, _) => OrderBookEntry(a0, a1)
    }
}
