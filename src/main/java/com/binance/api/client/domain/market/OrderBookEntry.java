package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An order book entry consisting of price and quantity.
 */
@JsonDeserialize(using = OrderBookEntryDeserializer.class)
public class OrderBookEntry {
  private final String price;
  private final String qty;

  @JsonCreator
  public OrderBookEntry(@JsonProperty("price") String price, @JsonProperty("qty") String qty) {
    this.price = price;
    this.qty = qty;
  }

  public String getPrice() {
    return price;
  }

  public String getQty() {
    return qty;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("price", price)
        .append("qty", qty)
        .toString();
  }
}
