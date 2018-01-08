package com.binance.api.client.domain.account;

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
  public final String asset;

  /**
   * Available balance.
   */
  public final String free;

  /**
   * Locked by open orders.
   */
  public final String locked;

}
