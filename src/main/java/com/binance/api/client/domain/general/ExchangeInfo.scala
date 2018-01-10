package com.binance.api.client.domain.general

import com.binance.api.client.domain.{Instant, Symbol}

/**
  * Current exchange trading rules and symbol information.
  * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
  */
case class ExchangeInfo(
    timezone:   String,
    serverTime: Instant,
    rateLimits: List[RateLimit],
    symbols:    List[SymbolInfo]
) {
  lazy val getSymbolInfo: Map[Symbol, SymbolInfo] =
    symbols.map(s => s.symbol -> s)(collection.breakOut)
}
