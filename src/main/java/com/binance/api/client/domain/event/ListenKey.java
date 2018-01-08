package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Dummy type to wrap a listen key from a server response.
 */
public class ListenKey {

  private final String listenKey;

  @JsonCreator
  public ListenKey(String listenKey) {
    this.listenKey = listenKey;
  }

  public String getListenKey() {
    return listenKey;
  }

  @Override
  public String toString() {
    return listenKey;
  }
}
