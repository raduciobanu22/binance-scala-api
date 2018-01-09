package com.binance.api.client

/**
  * Binance API error object.
  */
case class BinanceApiError(
    /**
      * Error code.
      */
    code: Int,
    /**
      * Error message.
      */
    msg: String
) {}

object BinanceApiError {
  import io.circe._

  implicit lazy val BinanceApiErrorDecoder: Decoder[BinanceApiError] =
    Decoder.forProduct2("code", "msg")(BinanceApiError.apply)
}