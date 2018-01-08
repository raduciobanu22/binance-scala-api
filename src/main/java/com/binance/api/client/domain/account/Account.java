package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Account information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
  @JsonCreator
  public Account(@JsonProperty("makerCommission") int makerCommission,
                 @JsonProperty("takerCommission") int takerCommission,
                 @JsonProperty("buyerCommission") int buyerCommission,
                 @JsonProperty("sellerCommission") int sellerCommission,
                 @JsonProperty("canTrade") boolean canTrade,
                 @JsonProperty("canWithdraw") boolean canWithdraw,
                 @JsonProperty("canDeposit") boolean canDeposit,
                 @JsonProperty("updateTime") long updateTime,
                 @JsonProperty("balances") List<AssetBalance> balances) {
    this.makerCommission = makerCommission;
    this.takerCommission = takerCommission;
    this.buyerCommission = buyerCommission;
    this.sellerCommission = sellerCommission;
    this.canTrade = canTrade;
    this.canWithdraw = canWithdraw;
    this.canDeposit = canDeposit;
    this.updateTime = updateTime;
    this.balances = balances;
  }

  /**
   * Maker commission.
   */
  public final int makerCommission;

  /**
   * Taker commission.
   */
  public final int takerCommission;

  /**
   * Buyer commission.
   */
  public final int buyerCommission;

  /**
   * Seller commission.
   */
  public final int sellerCommission;

  /**
   * Whether or not this account can trade.
   */
  public final boolean canTrade;

  /**
   * Whether or not it is possible to withdraw from this account.
   */
  public final boolean canWithdraw;

  /**
   * Whether or not it is possible to deposit into this account.
   */
  public final boolean canDeposit;

  /**
   * Last account update time.
   */
  public final long updateTime;

  /**
   * List of asset balances of this account.
   */
  public final List<AssetBalance> balances;

  /**
   * Returns the asset balance for a given symbol.
   *
   * @param symbol asset symbol to obtain the balances from
   * @return an asset balance for the given symbol which can be 0 in case the symbol has no balance in the account
   */
  public AssetBalance getAssetBalance(String symbol) {
    for (AssetBalance assetBalance : balances) {
      if (symbol.equals(assetBalance.asset)) {
        return assetBalance;
      }
    }
    AssetBalance emptyBalance = new AssetBalance(symbol, "0", "0");
    return emptyBalance;
  }

}
