package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Rate limits.
 */
public class RateLimit {

  private final RateLimitType rateLimitType;

  private final RateLimitInterval interval;

  private final Integer limit;

  @JsonCreator
  public RateLimit(@JsonProperty("rateLimitType") RateLimitType rateLimitType,
                   @JsonProperty("interval") RateLimitInterval interval,
                   @JsonProperty("limit") Integer limit) {
    this.rateLimitType = rateLimitType;
    this.interval = interval;
    this.limit = limit;
  }

  public RateLimitType getRateLimitType() {
    return rateLimitType;
  }

  public RateLimitInterval getInterval() {
    return interval;
  }

  public Integer getLimit() {
    return limit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("rateLimitType", rateLimitType)
        .append("interval", interval)
        .append("limit", limit)
        .toString();
  }
}
