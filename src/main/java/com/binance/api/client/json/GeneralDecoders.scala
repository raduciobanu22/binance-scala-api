package com.binance.api.client.json

import com.binance.api.client.domain.general._
import io.circe._, generic.semiauto.deriveDecoder

trait GeneralDecoders extends DomainDecoders {

  implicit lazy val FilterTypeDecoder: Decoder[FilterType] =
    JavaEnumDecoder(FilterType.values)

  implicit lazy val RateLimitTypeDecoder: Decoder[RateLimitType] =
    JavaEnumDecoder(RateLimitType.values)

  implicit lazy val RateLimitIntervalDecoder: Decoder[RateLimitInterval] =
    JavaEnumDecoder(RateLimitInterval.values)

  implicit lazy val SymbolStatusDecoder: Decoder[SymbolStatus] =
    JavaEnumDecoder(SymbolStatus.values)

  implicit lazy val ExchangeFilterDecoder: Decoder[ExchangeFilter] = deriveDecoder[ExchangeFilter]
  implicit lazy val RateLimitDecoder:      Decoder[RateLimit]      = deriveDecoder[RateLimit]
  implicit lazy val SymbolFilterDecoder:   Decoder[SymbolFilter]   = deriveDecoder[SymbolFilter]
  implicit lazy val SymbolInfoDecoder:     Decoder[SymbolInfo]     = deriveDecoder[SymbolInfo]
  implicit lazy val ExchangeInfoDecoder:   Decoder[ExchangeInfo]   = deriveDecoder[ExchangeInfo]
  implicit lazy val ServerTimeDecoder:     Decoder[ServerTime]     = deriveDecoder[ServerTime]
}
