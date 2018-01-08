package com.binance.api.client.domain.event;

import com.binance.api.client.domain.market.AggTrade;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An aggregated trade event for a symbol.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggTradeEvent extends AggTrade {

  @JsonCreator
  public AggTradeEvent(@JsonProperty("eventType") String eventType,
                       @JsonProperty("eventTime") long eventTime,
                       @JsonProperty("symbol") String symbol) {
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
  }

  @JsonProperty("e")
  private final String eventType;

  @JsonProperty("E")
  private final long eventTime;

  @JsonProperty("s")
  private final String symbol;

  public String getEventType() {
    return eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public String getSymbol() {
    return symbol;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime)
        .append("symbol", symbol)
        .append("aggTrade", super.toString())
        .toString();
  }
}
