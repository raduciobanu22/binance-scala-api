package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Filters define trading rules on a symbol or an exchange. Filters come in two forms: symbol filters and exchange filters.
 *
 * The PRICE_FILTER defines the price rules for a symbol.
 *
 * The LOT_SIZE filter defines the quantity (aka "lots" in auction terms) rules for a symbol.
 *
 * The MIN_NOTIONAL filter defines the minimum notional value allowed for an order on a symbol. An order's notional value is the price * quantity.
 *
 * The MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol. Note that both "algo" orders and normal orders are counted for this filter.
 *
 * The MAX_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol. "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
 */
public class SymbolFilter {

  // PRICE_FILTER

  private final FilterType filterType;

  /**
   * Defines the minimum price/stopPrice allowed.
   */
  private final String minPrice;

  /**
   * Defines the maximum price/stopPrice allowed.
   */
  private final String maxPrice;

  /**
   * Defines the intervals that a price/stopPrice can be increased/decreased by.
   */
  private final String tickSize;


  // LOT_SIZE

  /**
   * Defines the minimum quantity/icebergQty allowed.
   */
  private final String minQty;

  /**
   * Defines the maximum quantity/icebergQty allowed.
   */
  private final String maxQty;

  /**
   * Defines the intervals that a quantity/icebergQty can be increased/decreased by.
   */
  private final String stepSize;

  // MIN_NOTIONAL

  /**
   * Defines the minimum notional value allowed for an order on a symbol. An order's notional value is the price * quantity.
   */
  private final String minNotional;

  /**
   * MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol. Note that both "algo" orders and normal orders are counted for this filter.
   * MAX_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol. "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
   */
  private final String limit;

  @JsonCreator
  public SymbolFilter(@JsonProperty("filterType") FilterType filterType,
                      @JsonProperty("minPrice") String minPrice,
                      @JsonProperty("maxPrice") String maxPrice,
                      @JsonProperty("tickSize") String tickSize,
                      @JsonProperty("minQty") String minQty,
                      @JsonProperty("maxQty") String maxQty,
                      @JsonProperty("stepSize") String stepSize,
                      @JsonProperty("minNotional") String minNotional,
                      @JsonProperty("limit") String limit) {
    this.filterType = filterType;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.tickSize = tickSize;
    this.minQty = minQty;
    this.maxQty = maxQty;
    this.stepSize = stepSize;
    this.minNotional = minNotional;
    this.limit = limit;
  }

  public FilterType getFilterType() {
    return filterType;
  }

  public String getMinPrice() {
    return minPrice;
  }

  public String getMaxPrice() {
    return maxPrice;
  }

  public String getTickSize() {
    return tickSize;
  }

  public String getMinQty() {
    return minQty;
  }

  public String getMaxQty() {
    return maxQty;
  }

  public String getStepSize() {
    return stepSize;
  }

  public String getMinNotional() {
    return minNotional;
  }

  public String getLimit() {
    return limit;
  }

}
