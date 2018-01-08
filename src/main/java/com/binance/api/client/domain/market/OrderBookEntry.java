package com.binance.api.client.domain.market;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * An order book entry consisting of price and quantity.
 */
@JsonDeserialize(using = OrderBookEntryDeserializer.class)
public class OrderBookEntry {
  public final String price;
  public final String qty;

  @JsonCreator
  public OrderBookEntry(@JsonProperty("price") String price, @JsonProperty("qty") String qty) {
    this.price = price;
    this.qty = qty;
  }
}
