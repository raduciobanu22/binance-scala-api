package com.binance.api.client.json

import com.binance.api.client.domain._
import io.circe._

trait DomainDecoders {
  implicit lazy val InstantDecoder: Decoder[Instant] =
    Decoder.decodeLong.map(Instant.apply)

  implicit lazy val OrderIdDecoder: Decoder[OrderId] =
    Decoder.decodeLong.map(OrderId.apply)

  implicit lazy val AssetDecoder: Decoder[Asset] =
    Decoder.decodeString.map(Asset.apply)

  implicit lazy val SymbolDecoder: Decoder[Symbol] =
    Decoder.decodeString.map(Symbol.apply)

  implicit lazy val PriceDecoder: Decoder[Price] =
    Decoder.decodeBigDecimal.map(Price.apply)

  implicit lazy val PriceDiffDecoder: Decoder[PriceDiff] =
    Decoder.decodeBigDecimal.map(PriceDiff.apply)

  implicit lazy val PercentDecoder: Decoder[Percent] =
    Decoder.decodeBigDecimal.map(Percent.apply)

  implicit lazy val VolumeDecoder: Decoder[Volume] =
    Decoder.decodeBigDecimal.map(Volume.apply)

  implicit lazy val AmountDecoder: Decoder[Amount] =
    Decoder.decodeBigDecimal.map(Amount.apply)

  implicit lazy val QuantityDecoder: Decoder[Quantity] =
    Decoder.decodeBigDecimal.map(Quantity.apply)

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
    Decoder.decodeTuple3[Price, Quantity, Seq[String]].map {
      case (a0, a1, _) => OrderBookEntry(a0, a1)
    }
}
