package com.binance.api.client.domain.general

import com.binance.api.client.domain.{Asset, OrderType, Symbol}

/**
  * Symbol information (base/quote).
  */
case class SymbolInfo(symbol:             Symbol,
                      status:             SymbolStatus,
                      baseAsset:          Asset,
                      baseAssetPrecision: Int,
                      quoteAsset:         Asset,
                      quotePrecision:     Int,
                      orderTypes:         List[OrderType],
                      icebergAllowed:     Boolean,
                      filters:            List[SymbolFilter]) {

  /**
    * @param filterType filter type to filter for.
    * @return symbol filter information for the provided filter type.
    */
  lazy val getSymbolFilter: Map[FilterType, SymbolFilter] =
    filters.map(f => f.filterType -> f)(collection.breakOut)
}
