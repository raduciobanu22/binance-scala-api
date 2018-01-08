package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an executed trade.
 */
public class Trade {
  @JsonCreator
  public Trade(@JsonProperty("Long") Long id,
               @JsonProperty("price") String price,
               @JsonProperty("String") String qty,
               @JsonProperty("commission") String commission,
               @JsonProperty("commissionAsset") String commissionAsset,
               @JsonProperty("long") long time,
               @JsonProperty("buyer") boolean buyer,
               @JsonProperty("maker") boolean maker,
               @JsonProperty("bestMatch") boolean bestMatch,
               @JsonProperty("orderId") String orderId) {
    this.id = id;
    this.price = price;
    this.qty = qty;
    this.commission = commission;
    this.commissionAsset = commissionAsset;
    this.time = time;
    this.buyer = buyer;
    this.maker = maker;
    this.bestMatch = bestMatch;
    this.orderId = orderId;
  }

  /**
   * Trade id.
   */
  public final Long id;

  /**
   * Price.
   */
  public final String price;

  /**
   * Quantity.
   */
  public final String qty;

  /**
   * Commission.
   */
  public final String commission;

  /**
   * Asset on which commission is taken
   */
  public final String commissionAsset;

  /**
   * Trade execution time.
   */
  public final long time;

  @JsonProperty("isBuyer")
  public final boolean buyer;

  @JsonProperty("isMaker")
  public final boolean maker;

  @JsonProperty("isBestMatch")
  public final boolean bestMatch;

  public final String orderId;
}
