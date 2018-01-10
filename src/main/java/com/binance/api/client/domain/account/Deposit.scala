package com.binance.api.client.domain.account

import com.binance.api.client.domain.{Amount, Asset, Instant}

/**
  * A deposit that was done to a Binance account.
  */
case class Deposit(
    /**
      * Amount deposited.
      */
    amount: Amount,
    /**
      * Symbol.
      */
    asset: Asset,
    /**
      * Deposit time.
      */
    insertTime: Instant,
    /**
      * Transaction id
      */
    txId: String,
    /**
      * (0:pending,1:success)
      */
    status: Int
)
