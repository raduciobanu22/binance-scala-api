package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
  public final String amount;

  /**
   * Symbol.
   */
  public final String asset;

  /**
   * Deposit time.
   */
  public final String insertTime;

  /**
   * Transaction id
   */
  public final String txId;

  /**
   * (0:pending,1:success)
   */
  public final int status;

}
