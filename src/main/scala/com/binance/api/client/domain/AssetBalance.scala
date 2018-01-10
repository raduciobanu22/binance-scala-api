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
    asset: Asset,
    /**
      * Available balance.
      */
    free: Amount,
    /**
      * Locked by open orders.
      */
    locked: Amount
)
