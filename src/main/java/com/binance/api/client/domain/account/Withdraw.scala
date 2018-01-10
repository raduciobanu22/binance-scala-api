package com.binance.api.client.domain.account

import com.binance.api.client.domain.{Amount, Asset, Instant}

/**
  * A withdraw that was done to a Binance account.
  */
case class Withdraw(
    /**
      * Amount withdrawn.
      */
    amount: Amount,
    /**
      * Destination address.
      */
    address: String,
    /**
      * Symbol.
      */
    asset:       Asset,
    applyTime:   String,
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
