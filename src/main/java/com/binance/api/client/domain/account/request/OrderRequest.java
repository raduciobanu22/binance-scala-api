package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base request parameters for order-related methods.
 */
public class OrderRequest {
  @JsonCreator
  public OrderRequest(@JsonProperty("symbol") String symbol,
                      @JsonProperty("recvWindow") Long recvWindow,
                      @JsonProperty("timestamp") Long timestamp) {
    this.symbol = symbol;
    this.recvWindow = recvWindow;
    this.timestamp = timestamp;
  }

  public OrderRequest(String symbol) {
    this(symbol, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
  }

  private final String symbol;

  private final Long recvWindow;

  private final Long timestamp;

  public String getSymbol() {
    return symbol;
  }

  public Long getRecvWindow() {
    return recvWindow;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("recvWindow", recvWindow)
        .append("timestamp", timestamp)
        .toString();
  }
}
