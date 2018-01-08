package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  @JsonProperty("depositList")
  private final List<Deposit> depositList;

  private final boolean success;

  public List<Deposit> getDepositList() {
    return depositList;
  }

  public boolean isSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("depositList", depositList)
        .append("success", success)
        .toString();
  }
}
