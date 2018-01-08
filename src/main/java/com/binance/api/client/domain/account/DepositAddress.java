package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  private final String address;

  private final boolean success;

  private final String addressTag;

  private final String asset;

  public String getAddress() {
    return address;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getAddressTag() {
    return addressTag;
  }

  public String getAsset() {
    return asset;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("address", address)
        .append("success", success)
        .append("addressTag", addressTag)
        .append("asset", asset)
        .toString();
  }
}