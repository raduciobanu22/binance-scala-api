package com.binance.api.client.domain.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An asset balance in an Account.
 *
 * @see Account
 */
public class AssetBalance {

  public AssetBalance(String asset, String free, String locked) {
    this.asset = asset;
    this.free = free;
    this.locked = locked;
  }

  /**
   * Asset symbol.
   */
  private final String asset;

  /**
   * Available balance.
   */
  private final String free;

  /**
   * Locked by open orders.
   */
  private final String locked;

  public String getAsset() {
    return asset;
  }

  public String getFree() {
    return free;
  }

  public String getLocked() {
    return locked;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("asset", asset)
        .append("free", free)
        .append("locked", locked)
        .toString();
  }
}
