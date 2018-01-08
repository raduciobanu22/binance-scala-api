package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A withdraw that was done to a Binance account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Withdraw {
  @JsonCreator
  public Withdraw(@JsonProperty("amount") String amount,
                  @JsonProperty("address") String address,
                  @JsonProperty("asset") String asset,
                  @JsonProperty("applyTime") String applyTime,
                  @JsonProperty("successTime") String successTime,
                  @JsonProperty("txId") String txId,
                  @JsonProperty("id") String id,
                  @JsonProperty("status") int status) {
    this.amount = amount;
    this.address = address;
    this.asset = asset;
    this.applyTime = applyTime;
    this.successTime = successTime;
    this.txId = txId;
    this.id = id;
    this.status = status;
  }

  /**
   * Amount withdrawn.
   */
  private final String amount;

  /**
   * Destination address.
   */
  private final String address;

  /**
   * Symbol.
   */
  private final String asset;

  private final String applyTime;

  private final String successTime;

  /**
   * Transaction id.
   */
  private final String txId;

  /**
   * Id.
   */
  private final String id;

  /**
   * (0:Email Sent,1:Cancelled 2:Awaiting Approval 3:Rejected 4:Processing 5:Failure 6:Completed)
   */
  private final int status;

  public String getAmount() {
    return amount;
  }

  public String getAddress() {
    return address;
  }

  public String getAsset() {
    return asset;
  }

  public String getApplyTime() {
    return applyTime;
  }

  public String getSuccessTime() {
    return successTime;
  }

  public String getTxId() {
    return txId;
  }

  public String getId() {
    return id;
  }

  public int getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("amount", amount)
        .append("address", address)
        .append("asset", asset)
        .append("applyTime", applyTime)
        .append("successTime", successTime)
        .append("txId", txId)
        .append("id", id)
        .append("status", status)
        .toString();
  }
}
