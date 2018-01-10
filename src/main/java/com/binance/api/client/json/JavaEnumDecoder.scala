package com.binance.api.client.json

import io.circe.{Decoder, DecodingFailure, HCursor}

object JavaEnumDecoder {
  def apply[E <: java.lang.Enum[E]](values: Seq[E]): Decoder[E] =
    Decoder.decodeString.flatMap { str => (c: HCursor) =>
      values
        .find(_.name() == str)
        .toRight(DecodingFailure(s"$str not among $values", c.history))
    }
}
