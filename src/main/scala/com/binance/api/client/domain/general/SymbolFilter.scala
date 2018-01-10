package com.binance.api.client.domain.general

import com.binance.api.client.domain.{Price, Quantity}

/**
  * Filters define trading rules on a symbol or an exchange. Filters come in two forms: symbol filters and exchange filters.
  *
  * The PRICE_FILTER defines the price rules for a symbol.
  *
  * The LOT_SIZE filter defines the quantity (aka "lots" in auction terms) rules for a symbol.
  *
  * The MIN_NOTIONAL filter defines the minimum notional value allowed for an order on a symbol. An order's notional value is the price * quantity.
  *
  * The MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol. Note that both "algo" orders and normal orders are counted for this filter.
  *
  * The MAX_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol. "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
  */
case class SymbolFilter(
    filterType: FilterType,
    /**
      * Defines the minimum price/stopPrice allowed.
      */
    minPrice: Option[Price],
    /**
      * Defines the maximum price/stopPrice allowed.
      */
    maxPrice: Option[Price],
    /**
      * Defines the intervals that a price/stopPrice can be increased/decreased by.
      */
    tickSize: Option[String],
    /**
      * Defines the minimum quantity/icebergQty allowed.
      */
    minQty: Option[Quantity],
    /**
      * Defines the maximum quantity/icebergQty allowed.
      */
    maxQty: Option[Quantity],
    /**
      * Defines the intervals that a quantity/icebergQty can be increased/decreased by.
      */
    stepSize: Option[String],
    /**
      * Defines the minimum notional value allowed for an order on a symbol. An order's notional value is the price * quantity.
      */
    minNotional: Option[String],
    /**
      * MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol. Note that both "algo" orders and normal orders are counted for this filter.
      * MAX_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol. "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
      */
    limit: Option[String]
)
