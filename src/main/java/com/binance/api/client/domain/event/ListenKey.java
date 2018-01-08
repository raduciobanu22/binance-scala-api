package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Dummy type to wrap a listen key from a server response.
 */
public class ListenKey {

  public final String listenKey;

  @JsonCreator
  public ListenKey(String listenKey) {
    this.listenKey = listenKey;
  }
}
