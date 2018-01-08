package com.binance.api.client.domain.general;

import com.binance.api.client.domain.OrderType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Symbol information (base/quote).
 */
public class SymbolInfo {

  public final String symbol;

  public final SymbolStatus status;

  public final String baseAsset;

  public final Integer baseAssetPrecision;

  public final String quoteAsset;

  public final Integer quotePrecision;

  public final List<OrderType> orderTypes;

  public final boolean icebergAllowed;

  public final List<SymbolFilter> filters;

  @JsonCreator
  public SymbolInfo(@JsonProperty("symbol") String symbol,
                    @JsonProperty("status") SymbolStatus status,
                    @JsonProperty("baseAsset") String baseAsset,
                    @JsonProperty("baseAssetPrecision") Integer baseAssetPrecision,
                    @JsonProperty("quoteAsset") String quoteAsset,
                    @JsonProperty("quotePrecision") Integer quotePrecision,
                    @JsonProperty("orderTypes") List<OrderType> orderTypes,
                    @JsonProperty("icebergAllowed") boolean icebergAllowed,
                    @JsonProperty("filters") List<SymbolFilter> filters) {
    this.symbol = symbol;
    this.status = status;
    this.baseAsset = baseAsset;
    this.baseAssetPrecision = baseAssetPrecision;
    this.quoteAsset = quoteAsset;
    this.quotePrecision = quotePrecision;
    this.orderTypes = orderTypes;
    this.icebergAllowed = icebergAllowed;
    this.filters = filters;
  }

  /**
   * @param filterType filter type to filter for.
   * @return symbol filter information for the provided filter type.
   */
  public SymbolFilter getSymbolFilter(FilterType filterType) {
    return filters.stream()
        .filter(symbolFilter -> symbolFilter.filterType == filterType)
        .findFirst()
        .get();
  }

}
