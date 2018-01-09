package com.binance.api.client.domain.account

/**
  * A deposit that was done to a Binance account.
  */
case class Deposit(
    /**
      * Amount deposited.
      */
    amount: BigDecimal,
    /**
      * Symbol.
      */
    asset: String,
    /**
      * Deposit time.
      */
    insertTime: Long,
    /**
      * Transaction id
      */
    txId: String,
    /**
      * (0:pending,1:success)
      */
    status: Int
)
