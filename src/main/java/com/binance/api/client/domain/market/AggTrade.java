package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.OptionalLong;

/**
 * An aggregated trade event for a symbol.
 */

public class AggTrade {

  private final long aggregatedTradeId;
  private final String price;
  private final String quantity;
  private final long firstBreakdownTradeId;
  private final long lastBreakdownTradeId;
  private final OptionalLong tradeTime;
  private final boolean isBuyerMaker;
  private final boolean wasTradeBestPriceMatch;

  @JsonCreator
  @JsonIgnoreProperties(ignoreUnknown = true)
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

  public long getAggregatedTradeId() {
    return aggregatedTradeId;
  }

  public String getPrice() {
    return price;
  }

  public String getQuantity() {
    return quantity;
  }

  public long getFirstBreakdownTradeId() {
    return firstBreakdownTradeId;
  }

  public long getLastBreakdownTradeId() {
    return lastBreakdownTradeId;
  }

  public OptionalLong getTradeTime() {
    return tradeTime;
  }

  public boolean isBuyerMaker() {
    return isBuyerMaker;
  }

  public boolean isWasTradeBestPriceMatch() {
    return wasTradeBestPriceMatch;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("aggregatedTradeId", aggregatedTradeId)
        .append("price", price)
        .append("quantity", quantity)
        .append("firstBreakdownTradeId", firstBreakdownTradeId)
        .append("lastBreakdownTradeId", lastBreakdownTradeId)
        .append("tradeTime", tradeTime)
        .append("isBuyerMaker", isBuyerMaker)
        .append("wasTradeBestPriceMatch", wasTradeBestPriceMatch)
        .toString();
  }
}
