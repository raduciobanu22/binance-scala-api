package com.binance.api.client.domain.general

import com.binance.api.client.domain.DomainJson.{enumDecoder, OrderTypeDecoder}

object GeneralJson {
  import io.circe._

  implicit lazy val FilterTypeDecoder: Decoder[FilterType] =
    enumDecoder(FilterType.values)

  implicit lazy val RateLimitTypeDecoder: Decoder[RateLimitType] =
    enumDecoder(RateLimitType.values)

  implicit lazy val RateLimitIntervalDecoder: Decoder[RateLimitInterval] =
    enumDecoder(RateLimitInterval.values)

  implicit lazy val SymbolStatusDecoder: Decoder[SymbolStatus] =
    enumDecoder(SymbolStatus.values)

  implicit lazy val ExchangeFilterDecoder: Decoder[ExchangeFilter] =
    Decoder.forProduct2("filterType", "limit")(ExchangeFilter.apply)

  implicit lazy val RateLimitDecoder: Decoder[RateLimit] =
    Decoder.forProduct3("rateLimitType", "interval", "limit")(RateLimit.apply)

  implicit lazy val SymbolFilterDecoder: Decoder[SymbolFilter] =
    Decoder.forProduct9("filterType",
                        "minPrice",
                        "maxPrice",
                        "tickSize",
                        "minQty",
                        "maxQty",
                        "stepSize",
                        "minNotional",
                        "limit",
    )(SymbolFilter.apply)

  implicit lazy val SymbolInfoDecoder: Decoder[SymbolInfo] =
    Decoder.forProduct9("symbol",
                        "status",
                        "baseAsset",
                        "baseAssetPrecision",
                        "quoteAsset",
                        "quotePrecision",
                        "orderTypes",
                        "icebergAllowed",
                        "filters")(SymbolInfo.apply)

  implicit lazy val ExchangeInfoDecoder: Decoder[ExchangeInfo] =
    Decoder.forProduct4("timezone", "serverTime", "rateLimits", "symbols")(ExchangeInfo.apply)

  implicit lazy val ServerTimeDecoder: Decoder[ServerTime] =
    Decoder.forProduct1("serverTime")(ServerTime.apply)

}
