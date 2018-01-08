package com.binance.api.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Binance API error object.
 */
public class BinanceApiError {

  /**
   * Error code.
   */
  private final int code;

  /**
   * Error message.
   */
  private final String msg;

  @JsonCreator
  public BinanceApiError(@JsonProperty("code") int code,
                         @JsonProperty("msg") String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("code", code)
        .append("msg", msg)
        .toString();
  }
}
