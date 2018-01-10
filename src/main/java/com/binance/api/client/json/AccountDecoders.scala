package com.binance.api.client.json

import com.binance.api.client.domain.account._
import io.circe._, generic.semiauto.deriveDecoder

trait AccountDecoders extends DomainDecoders {
  implicit lazy val WithdrawDecoder:         Decoder[Withdraw]         = deriveDecoder[Withdraw]
  implicit lazy val WithdrawHistoryDecoder:  Decoder[WithdrawHistory]  = deriveDecoder[WithdrawHistory]
  implicit lazy val DepositDecoder:          Decoder[Deposit]          = deriveDecoder[Deposit]
  implicit lazy val DepositHistoryDecoder:   Decoder[DepositHistory]   = deriveDecoder[DepositHistory]
  implicit lazy val NewOrderResponseDecoder: Decoder[NewOrderResponse] = deriveDecoder[NewOrderResponse]
  implicit lazy val OrderDecoder:            Decoder[Order]            = deriveDecoder[Order]
  implicit lazy val AccountDecoder:          Decoder[Account]          = deriveDecoder[Account]
  implicit lazy val TradeDecoder:            Decoder[Trade]            = deriveDecoder[Trade]
  implicit lazy val DepositAddressDecoder:   Decoder[DepositAddress]   = deriveDecoder[DepositAddress]
}
