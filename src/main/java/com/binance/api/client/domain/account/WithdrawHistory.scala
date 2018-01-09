package com.binance.api.client.domain.account

/**
  * History of account withdrawals.
  *
  * @see Withdraw
  */
case class WithdrawHistory(withdrawList: List[Withdraw], success: Boolean)
