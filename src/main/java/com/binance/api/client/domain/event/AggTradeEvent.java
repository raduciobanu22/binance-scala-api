package com.binance.api.client.domain.event;

import com.binance.api.client.domain.market.AggTrade;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.OptionalLong;

/**
 * An aggregated trade event for a symbol.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggTradeEvent extends AggTrade {
  @JsonCreator
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

  public final String eventType;
  public final long eventTime;
  public final String symbol;

}
