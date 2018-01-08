package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.OptionalLong;

/**
 * An aggregated trade event for a symbol.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggTrade {

  public final long aggregatedTradeId;
  public final String price;
  public final String quantity;
  public final long firstBreakdownTradeId;
  public final long lastBreakdownTradeId;
  public final OptionalLong tradeTime;
  public final boolean isBuyerMaker;
  public final boolean wasTradeBestPriceMatch;

  @JsonCreator
  public AggTrade(@JsonProperty("a") long aggregatedTradeId,
                  @JsonProperty("p") String price,
                  @JsonProperty("q") String quantity,
                  @JsonProperty("f") long firstBreakdownTradeId,
                  @JsonProperty("l") long lastBreakdownTradeId,
                  @JsonProperty("T") OptionalLong tradeTime,
                  @JsonProperty("m") boolean isBuyerMaker,
                  @JsonProperty("M") boolean wasTradeBestPriceMatch
                  ) {

    this.aggregatedTradeId = aggregatedTradeId;
    this.price = price;
    this.quantity = quantity;
    this.firstBreakdownTradeId = firstBreakdownTradeId;
    this.lastBreakdownTradeId = lastBreakdownTradeId;
    this.tradeTime = tradeTime;
    this.isBuyerMaker = isBuyerMaker;
    this.wasTradeBestPriceMatch = wasTradeBestPriceMatch;
  }
}
