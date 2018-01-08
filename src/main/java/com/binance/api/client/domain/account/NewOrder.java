package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

/**
 * A trade order to enter or exit a position.
 */
public class NewOrder {

  /**
   * Symbol to place the order on.
   */
  private final String symbol;

  /**
   * Buy/Sell order side.
   */
  private final OrderSide side;

  /**
   * Type of order.
   */
  private final OrderType type;

  /**
   * Time in force to indicate how long will the order remain active.
   */
  private final TimeInForce timeInForce;

  /**
   * Quantity.
   */
  private final String quantity;

  /**
   * Price.
   */
  private final Optional<String> price;

  /**
   * A unique id for the order. Automatically generated if not sent.
   */
  private final Optional<String> newClientOrderId;

  /**
   * Used with stop orders.
   */
  private final Optional<String> stopPrice;

  /**
   * Used with iceberg orders.
   */
  private final Optional<String> icebergQty;

  /**
   * Receiving window.
   */
  private final Long recvWindow;

  /**
   * Order timestamp.
   */
  private final long timestamp;

  @JsonCreator
  public NewOrder(@JsonProperty("symbol") String symbol,
                  @JsonProperty("OrderSide") OrderSide side,
                  @JsonProperty("OrderType") OrderType type,
                  @JsonProperty("timeInForce") TimeInForce timeInForce,
                  @JsonProperty("quantity") String quantity,
                  @JsonProperty("price") Optional<String> price,
                  @JsonProperty("newClientOrderId") Optional<String> newClientOrderId,
                  @JsonProperty("stopPrice") Optional<String> stopPrice,
                  @JsonProperty("icebergQty") Optional<String> icebergQty,
                  @JsonProperty("recvWindow") Long recvWindow,
                  @JsonProperty("timestamp") long timestamp) {
    this.symbol = symbol;
    this.side = side;
    this.type = type;
    this.timeInForce = timeInForce;
    this.quantity = quantity;
    this.price = price;
    this.newClientOrderId = newClientOrderId;
    this.stopPrice = stopPrice;
    this.icebergQty = icebergQty;
    this.recvWindow = recvWindow;
    this.timestamp = timestamp;
  }

  /**
   * Creates a new order with all required parameters.
   */
  public NewOrder(String symbol, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity) {
    this(symbol,
        side,
        type,
        timeInForce,
        quantity,
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
        System.currentTimeMillis()
    );
  }

  /**
   * Creates a new order with all required parameters plus price, which is optional for MARKET orders.
   */
  public NewOrder(String symbol, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity, String price) {
    this(symbol,
        side,
        type,
        timeInForce,
        quantity,
        Optional.of(price),
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
        System.currentTimeMillis()
    );
  }

  public String getSymbol() {
    return symbol;
  }

  public OrderSide getSide() {
    return side;
  }

  public OrderType getType() {
    return type;
  }

  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  public String getQuantity() {
    return quantity;
  }

  public Optional<String> getPrice() {
    return price;
  }

  public Optional<String> getNewClientOrderId() {
    return newClientOrderId;
  }

  public Optional<String> getStopPrice() {
    return stopPrice;
  }

  public Optional<String> getIcebergQty() {
    return icebergQty;
  }

  public Long getRecvWindow() {
    return recvWindow;
  }

  public long getTimestamp() {
    return timestamp;
  }

  /**
   * Places a MARKET buy order for the given <code>quantity</code>.
   *
   * @return a new order which is pre-configured with MARKET as the order type and BUY as the order side.
   */
  public static NewOrder marketBuy(String symbol, String quantity) {
    return new NewOrder(symbol, OrderSide.BUY, OrderType.MARKET, null, quantity);
  }

  /**
   * Places a MARKET sell order for the given <code>quantity</code>.
   *
   * @return a new order which is pre-configured with MARKET as the order type and SELL as the order side.
   */
  public static NewOrder marketSell(String symbol, String quantity) {
    return new NewOrder(symbol, OrderSide.SELL, OrderType.MARKET, null, quantity);
  }

  /**
   * Places a LIMIT buy order for the given <code>quantity</code> and <code>price</code>.
   *
   * @return a new order which is pre-configured with LIMIT as the order type and BUY as the order side.
   */
  public static NewOrder limitBuy(String symbol, TimeInForce timeInForce, String quantity, String price) {
    return new NewOrder(symbol, OrderSide.BUY, OrderType.LIMIT, timeInForce, quantity, price);
  }

  /**
   * Places a LIMIT sell order for the given <code>quantity</code> and <code>price</code>.
   *
   * @return a new order which is pre-configured with LIMIT as the order type and SELL as the order side.
   */
  public static NewOrder limitSell(String symbol, TimeInForce timeInForce, String quantity, String price) {
    return new NewOrder(symbol, OrderSide.SELL, OrderType.LIMIT, timeInForce, quantity, price);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("symbol", symbol)
        .append("side", side)
        .append("type", type)
        .append("timeInForce", timeInForce)
        .append("quantity", quantity)
        .append("price", price)
        .append("newClientOrderId", newClientOrderId)
        .append("stopPrice", stopPrice)
        .append("icebergQty", icebergQty)
        .append("recvWindow", recvWindow)
        .append("timestamp", timestamp)
        .toString();
  }
}
