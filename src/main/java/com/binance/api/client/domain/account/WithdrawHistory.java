package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * History of account withdrawals.
 *
 * @see Withdraw
 */
public class WithdrawHistory {
  @JsonCreator
  public WithdrawHistory(@JsonProperty("withdrawList") List<Withdraw> withdrawList,
                         @JsonProperty("success") boolean success) {
    this.withdrawList = withdrawList;
    this.success = success;
  }

  public final List<Withdraw> withdrawList;

  public final boolean success;
}
