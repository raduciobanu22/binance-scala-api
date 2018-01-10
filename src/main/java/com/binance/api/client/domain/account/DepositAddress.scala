package com.binance.api.client.domain.account

import com.binance.api.client.domain.Asset

/**
  * A deposit address for a given asset.
  */
case class DepositAddress(
    address:    String,
    success:    Boolean,
    addressTag: String,
    asset:      Asset
)
