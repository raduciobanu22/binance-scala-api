package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  private final List<Withdraw> withdrawList;

  private final boolean success;

  public List<Withdraw> getWithdrawList() {
    return withdrawList;
  }

  public boolean isSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("withdrawList", withdrawList)
        .append("success", success)
        .toString();
  }
}
