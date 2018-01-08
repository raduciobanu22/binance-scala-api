package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * History of account deposits.
 *
 * @see Deposit
 */
public class DepositHistory {
  @JsonCreator
  public DepositHistory(@JsonProperty("depositList") List<Deposit> depositList,
                        @JsonProperty("success") boolean success) {
    this.depositList = depositList;
    this.success = success;
  }

  public final List<Deposit> depositList;

  public final boolean success;

}
