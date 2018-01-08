package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
  public final String amount;

  /**
   * Destination address.
   */
  public final String address;

  /**
   * Symbol.
   */
  public final String asset;

  public final String applyTime;

  public final String successTime;

  /**
   * Transaction id.
   */
  public final String txId;

  /**
   * Id.
   */
  public final String id;

  /**
   * (0:Email Sent,1:Cancelled 2:Awaiting Approval 3:Rejected 4:Processing 5:Failure 6:Completed)
   */
  public final int status;
}
