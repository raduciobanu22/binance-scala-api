package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A deposit that was done to a Binance account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {
  @JsonCreator
  public Deposit(String amount, String asset, String insertTime, String txId, int status) {
    this.amount = amount;
    this.asset = asset;
    this.insertTime = insertTime;
    this.txId = txId;
    this.status = status;
  }

  /**
   * Amount deposited.
   */
  private final String amount;

  /**
   * Symbol.
   */
  private final String asset;

  /**
   * Deposit time.
   */
  private final String insertTime;

  /**
   * Transaction id
   */
  private final String txId;

  /**
   * (0:pending,1:success)
   */
  private final int status;

  public String getAmount() {
    return amount;
  }

  public String getAsset() {
    return asset;
  }

  public String getInsertTime() {
    return insertTime;
  }

  public String getTxId() {
    return txId;
  }

  public int getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("amount", amount)
        .append("asset", asset)
        .append("insertTime", insertTime)
        .append("txId", txId)
        .append("status", status)
        .toString();
  }
}
