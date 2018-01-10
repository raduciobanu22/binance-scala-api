package com.binance.api.client.exception

import com.binance.api.client.BinanceApiError

final case class BinanceApiExceptionError(error: BinanceApiError) extends RuntimeException(error.msg)
final case class BinanceApiExceptionMsg(value:   String)          extends RuntimeException(value)
final case class BinanceApiExceptionCause(cause: Throwable)       extends RuntimeException(cause)
