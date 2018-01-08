package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Time of the server running Binance's REST API.
 */
public class ServerTime {
  private final Long serverTime;

  @JsonCreator
  public ServerTime(Long serverTime) {
    this.serverTime = serverTime;
  }

  public Long getServerTime() {
    return serverTime;
  }

  @Override
  public String toString() {
    return String.valueOf(serverTime);
  }
}
