package com.binance.api.client.domain.account

/**
  * A deposit address for a given asset.
  */
case class DepositAddress(
    address: String,
    success: Boolean,
    addressTag: String,
    asset: String
)
