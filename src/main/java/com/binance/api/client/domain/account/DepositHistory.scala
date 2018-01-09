package com.binance.api.client.domain.account

/**
  * History of account deposits.
  *
  * @see Deposit
  */
case class DepositHistory(depositList: List[Deposit], success: Boolean)
