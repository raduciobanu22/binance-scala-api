package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Time of the server running Binance's REST API.
 */
public class ServerTime {
  public final Long serverTime;

  @JsonCreator
  public ServerTime(@JsonProperty("serverTime") Long serverTime) {
    this.serverTime = serverTime;
  }
}
