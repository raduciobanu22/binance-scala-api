package com.binance.api.client.domain.general

import com.binance.api.client.domain.OrderType

/**
  * Symbol information (base/quote).
  */
case class SymbolInfo(symbol:             String,
                      status:             SymbolStatus,
                      baseAsset:          String,
                      baseAssetPrecision: Integer,
                      quoteAsset:         String,
                      quotePrecision:     Integer,
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
