package com.binance.api.client.domain.general

/**
  * Rate limits.
  */
case class RateLimit(
    rateLimitType: RateLimitType,
    interval:      RateLimitInterval,
    limit:         Int
)
