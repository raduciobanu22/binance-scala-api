package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
  private final Long id;

  /**
   * Price.
   */
  private final String price;

  /**
   * Quantity.
   */
  private final String qty;

  /**
   * Commission.
   */
  private final String commission;

  /**
   * Asset on which commission is taken
   */
  private final String commissionAsset;

  /**
   * Trade execution time.
   */
  private final long time;

  @JsonProperty("isBuyer")
  private final boolean buyer;

  @JsonProperty("isMaker")
  private final boolean maker;

  @JsonProperty("isBestMatch")
  private final boolean bestMatch;

  private final String orderId;

  public Long getId() {
    return id;
  }

  public String getPrice() {
    return price;
  }

  public String getQty() {
    return qty;
  }

  public String getCommission() {
    return commission;
  }

  public String getCommissionAsset() {
    return commissionAsset;
  }

  public long getTime() {
    return time;
  }

  public boolean isBuyer() {
    return buyer;
  }

  public boolean isMaker() {
    return maker;
  }

  public boolean isBestMatch() {
    return bestMatch;
  }

  public String getOrderId() {
    return orderId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("price", price)
        .append("qty", qty)
        .append("commission", commission)
        .append("commissionAsset", commissionAsset)
        .append("time", time)
        .append("buyer", buyer)
        .append("maker", maker)
        .append("bestMatch", bestMatch)
        .append("orderId", orderId)
        .toString();
  }
}
