package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
  private final int makerCommission;

  /**
   * Taker commission.
   */
  private final int takerCommission;

  /**
   * Buyer commission.
   */
  private final int buyerCommission;

  /**
   * Seller commission.
   */
  private final int sellerCommission;

  /**
   * Whether or not this account can trade.
   */
  private final boolean canTrade;

  /**
   * Whether or not it is possible to withdraw from this account.
   */
  private final boolean canWithdraw;

  /**
   * Whether or not it is possible to deposit into this account.
   */
  private final boolean canDeposit;

  /**
   * Last account update time.
   */
  private long updateTime;

  /**
   * List of asset balances of this account.
   */
  private List<AssetBalance> balances;

  public int getMakerCommission() {
    return makerCommission;
  }

  public int getTakerCommission() {
    return takerCommission;
  }

  public int getBuyerCommission() {
    return buyerCommission;
  }

  public int getSellerCommission() {
    return sellerCommission;
  }

  public boolean isCanTrade() {
    return canTrade;
  }

  public boolean isCanWithdraw() {
    return canWithdraw;
  }

  public boolean isCanDeposit() {
    return canDeposit;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  public List<AssetBalance> getBalances() {
    return balances;
  }

  public void setBalances(List<AssetBalance> balances) {
    this.balances = balances;
  }

  /**
   * Returns the asset balance for a given symbol.
   *
   * @param symbol asset symbol to obtain the balances from
   * @return an asset balance for the given symbol which can be 0 in case the symbol has no balance in the account
   */
  public AssetBalance getAssetBalance(String symbol) {
    for (AssetBalance assetBalance : balances) {
      if (symbol.equals(assetBalance.getAsset())) {
        return assetBalance;
      }
    }
    AssetBalance emptyBalance = new AssetBalance(symbol, "0", "0");
    return emptyBalance;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("makerCommission", makerCommission)
        .append("takerCommission", takerCommission)
        .append("buyerCommission", buyerCommission)
        .append("sellerCommission", sellerCommission)
        .append("canTrade", canTrade)
        .append("canWithdraw", canWithdraw)
        .append("canDeposit", canDeposit)
        .append("updateTime", updateTime)
        .append("balances", balances)
        .toString();
  }
}
