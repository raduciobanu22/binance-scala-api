package com.binance.api.client.domain.event;

import com.binance.api.client.domain.market.AggTrade;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.OptionalLong;

/**
 * An aggregated trade event for a symbol.
 */
public class AggTradeEvent extends AggTrade {
  @JsonCreator
  @JsonIgnoreProperties(ignoreUnknown = true)
  public AggTradeEvent(@JsonProperty("e") String eventType,
                       @JsonProperty("E") long eventTime,
                       @JsonProperty("s") String symbol,
                       @JsonProperty("a") long aggregatedTradeId,
                       @JsonProperty("p") String price,
                       @JsonProperty("q") String quantity,
                       @JsonProperty("f") long firstBreakdownTradeId,
                       @JsonProperty("l") long lastBreakdownTradeId,
                       @JsonProperty("T") OptionalLong tradeTime,
                       @JsonProperty("m") boolean isBuyerMaker,
                       @JsonProperty("M") boolean wasTradeBestPriceMatch) {
    super(aggregatedTradeId, price, quantity, firstBreakdownTradeId, lastBreakdownTradeId, tradeTime, isBuyerMaker, wasTradeBestPriceMatch);
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.symbol = symbol;
  }

  private final String eventType;
  private final long eventTime;
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
