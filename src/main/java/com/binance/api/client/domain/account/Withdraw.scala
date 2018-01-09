package com.binance.api.client.domain.account

/**
  * A withdraw that was done to a Binance account.
  */
case class Withdraw(
    /**
      * Amount withdrawn.
      */
    amount: BigDecimal,
    /**
      * Destination address.
      */
    address: String,
    /**
      * Symbol.
      */
    asset: String,
    applyTime: String,
    successTime: String,
    /**
      * Transaction id.
      */
    txId: String,
    /**
      * Id.
      */
    id: String,
    /**
      * (0:Email Sent,1:Cancelled 2:Awaiting Approval 3:Rejected 4:Processing 5:Failure 6:Completed)
      */
    status: Int
)
