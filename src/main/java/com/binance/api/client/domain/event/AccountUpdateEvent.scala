package com.binance.api.client.domain.event

import com.binance.api.client.domain.AssetBalance

/**
  * Account update event which will reflect the current position/balances of the account.
  *
  * This event is embedded as part of a user data update event.
  *
  * @see UserDataUpdateEvent
  */
case class AccountUpdateEvent(
    eventType: String,
    eventTime: Long,
    balances: List[AssetBalance]
)
