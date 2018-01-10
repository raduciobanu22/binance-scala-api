package com.binance.api.client.impl

import java.io.IOException

import com.binance.api.client.exception.{BinanceApiExceptionCause, BinanceApiExceptionMsg}
import io.circe._
import okhttp3.{Response, WebSocket, WebSocketListener}

/**
  * Binance API WebSocket listener.
  */
class BinanceApiWebSocketListener[T: Decoder](var callback: T => Unit) extends WebSocketListener {
  override def onMessage(webSocket: WebSocket, text: String): Unit =
    try {
      parser.decode[T](text) match {
        case Right(event) => callback(event)
        case Left(error)  => throw BinanceApiExceptionMsg(s"Couldn't decode $text: $error")
      }

    } catch {
      case e: IOException =>
        throw BinanceApiExceptionCause(e)
    }

  override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit =
    throw BinanceApiExceptionCause(t)
}
