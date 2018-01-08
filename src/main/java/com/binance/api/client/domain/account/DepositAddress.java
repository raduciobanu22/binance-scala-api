package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A deposit address for a given asset.
 */
public class DepositAddress {
  @JsonCreator
  public DepositAddress(@JsonProperty("address") String address,
                        @JsonProperty("success") boolean success,
                        @JsonProperty("addressTag") String addressTag,
                        @JsonProperty("asset") String asset) {
    this.address = address;
    this.success = success;
    this.addressTag = addressTag;
    this.asset = asset;
  }

  public final String address;

  public final boolean success;

  public final String addressTag;

  public final String asset;
}