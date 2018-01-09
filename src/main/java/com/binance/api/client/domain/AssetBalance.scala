package com.binance.api.client.domain

/**
  * An asset balance in an Account.
  *
  * @see Account
  */
case class AssetBalance(
    /**
      * Asset symbol.
      */
    asset: String,
    /**
      * Available balance.
      */
    free: String,
    /**
      * Locked by open orders.
      */
    locked: String
)
