package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rate limits.
 */
public class RateLimit {

  public final RateLimitType rateLimitType;

  public final RateLimitInterval interval;

  public final Integer limit;

  @JsonCreator
  public RateLimit(@JsonProperty("rateLimitType") RateLimitType rateLimitType,
                   @JsonProperty("interval") RateLimitInterval interval,
                   @JsonProperty("limit") Integer limit) {
    this.rateLimitType = rateLimitType;
    this.interval = interval;
    this.limit = limit;
  }
}
