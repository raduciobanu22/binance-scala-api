package com.binance.api.client.domain.general

import com.binance.api.client.domain.Instant

/**
  * Time of the server running Binance's REST API.
  */
case class ServerTime(serverTime: Instant)
