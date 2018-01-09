package com.binance.api.client.domain.account

import com.binance.api.client.domain.DomainJson._
import io.circe._

object AccountJson {
  implicit lazy val WithdrawDecoder: Decoder[Withdraw] =
    Decoder.forProduct8("amount", "address", "asset", "applyTime", "successTime", "txId", "id", "status")(
      Withdraw.apply
    )

  implicit lazy val WithdrawHistoryDecoder: Decoder[WithdrawHistory] =
    Decoder.forProduct2("withdrawList", "success")(WithdrawHistory.apply)

  implicit lazy val DepositDecoder: Decoder[Deposit] =
    Decoder.forProduct5("amount", "asset", "insertTime", "txId", "status")(Deposit.apply)

  implicit lazy val DepositHistoryDecoder: Decoder[DepositHistory] =
    Decoder.forProduct2("depositList", "success")(DepositHistory.apply)

  implicit lazy val NewOrderResponseDecoder: Decoder[NewOrderResponse] =
    Decoder.forProduct4("symbol", "orderId", "clientOrderId", "transactTime")(NewOrderResponse.apply)

  implicit lazy val OrderDecoder: Decoder[Order] =
    Decoder.forProduct13("symbol",
                         "orderId",
                         "clientOrderId",
                         "price",
                         "origQty",
                         "executedQty",
                         "status",
                         "timeInForce",
                         "type",
                         "side",
                         "stopPrice",
                         "icebergQty",
                         "time")(Order.apply)

  implicit lazy val AccountDecoder: Decoder[Account] =
    Decoder.forProduct9("makerCommission",
                        "takerCommission",
                        "buyerCommission",
                        "sellerCommission",
                        "canTrade",
                        "canWithdraw",
                        "canDeposit",
                        "updateTime",
                        "balances")(Account.apply)

  implicit lazy val TradeDecoder: Decoder[Trade] =
    Decoder.forProduct10("id",
                         "price",
                         "qty",
                         "commission",
                         "commissionAsset",
                         "time",
                         "buyer",
                         "maker",
                         "bestMatch",
                         "orderId")(Trade.apply)

  implicit lazy val DepositAddressDecoder: Decoder[DepositAddress] =
    Decoder.forProduct4("address", "success", "addressTag", "asset")(DepositAddress.apply)
}
