package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

/**
 * Base request parameters for order-related methods.
 */
public class OrderRequest {
  @JsonCreator
  public OrderRequest(@JsonProperty("symbol") String symbol,
                      @JsonProperty("recvWindow") Optional<Long> recvWindow,
                      @JsonProperty("timestamp") Optional<Long> timestamp) {
    this.symbol = symbol;
    this.recvWindow = recvWindow;
    this.timestamp = timestamp;
  }

  public OrderRequest(String symbol) {
    this(symbol, Optional.empty(), Optional.empty());
  }

  private final String symbol;

  private final Optional<Long> recvWindow;

  private final Optional<Long> timestamp;

  public String getSymbol() {
    return symbol;
  }

  public Optional<Long> getRecvWindow() {
    return recvWindow;
  }

  public Optional<Long> getTimestamp() {
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
