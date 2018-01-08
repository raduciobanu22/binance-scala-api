package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Exchange Filters define trading rules an exchange.
 *
 * The MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on the exchange. Note that both "algo" orders and normal orders are counted for this filter.
 *
 * The MAX_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on the exchange. "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
 */
public class ExchangeFilter {

  private final FilterType filterType;

  private final Integer limit;

  @JsonCreator
  public ExchangeFilter(@JsonProperty("filterType") FilterType filterType,
                        @JsonProperty("limit") Integer limit) {
    this.filterType = filterType;
    this.limit = limit;
  }

  public FilterType getFilterType() {
    return filterType;
  }

  public Integer getLimit() {
    return limit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("filterType", filterType)
        .append("limit", limit)
        .toString();
  }
}
