package com.binance.api.client.domain.general;

import com.binance.api.client.domain.OrderType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Symbol information (base/quote).
 */
public class SymbolInfo {

  private final String symbol;

  private final SymbolStatus status;

  private final String baseAsset;

  private final Integer baseAssetPrecision;

  private final String quoteAsset;

  private final Integer quotePrecision;

  private final List<OrderType> orderTypes;

  private final boolean icebergAllowed;

  private final List<SymbolFilter> filters;

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

  public String getSymbol() {
    return symbol;
  }

  public SymbolStatus getStatus() {
    return status;
  }

  public String getBaseAsset() {
    return baseAsset;
  }

  public Integer getBaseAssetPrecision() {
    return baseAssetPrecision;
  }

  public String getQuoteAsset() {
    return quoteAsset;
  }

  public Integer getQuotePrecision() {
    return quotePrecision;
  }

  public List<OrderType> getOrderTypes() {
    return orderTypes;
  }

  public boolean isIcebergAllowed() {
    return icebergAllowed;
  }

  public List<SymbolFilter> getFilters() {
    return filters;
  }

  /**
   * @param filterType filter type to filter for.
   * @return symbol filter information for the provided filter type.
   */
  public SymbolFilter getSymbolFilter(FilterType filterType) {
    return filters.stream()
        .filter(symbolFilter -> symbolFilter.getFilterType() == filterType)
        .findFirst()
        .get();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("status", status)
        .append("baseAsset", baseAsset)
        .append("baseAssetPrecision", baseAssetPrecision)
        .append("quoteAsset", quoteAsset)
        .append("quotePrecision", quotePrecision)
        .append("orderTypes", orderTypes)
        .append("icebergAllowed", icebergAllowed)
        .append("filters", filters)
        .toString();
  }
}
