package com.binance.api.domain.event;


import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.ExecutionType;
import com.binance.api.client.domain.OrderRejectReason;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests that JSON responses from the stream API are converted to the appropriate object.
 */
public class UserDataUpdateEventDeserializerTest {

  @Test
  public void testAccountUpdateEventDeserializer() {
    final String accountUpdateJson = "{\"e\":\"outboundAccountInfo\",\"E\":1,\"m\":10,\"t\":10,\"b\":0,\"s\":0,\"T\":true,\"W\":true,\"D\":true,\"B\":[{\"a\":\"BTC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"LTC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ETH\",\"f\":\"0.10000000\",\"l\":\"0.00000000\"},{\"a\":\"BNC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ICO\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"NEO\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"BNB\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"123\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"456\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"QTUM\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"EOS\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"SNT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"BNT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"GAS\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"BCC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"BTM\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"USDT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"HCC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"HSR\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"OAX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"DNT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"MCO\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ICN\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ELC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"PAY\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ZRX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"OMG\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"WTC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"LRX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"YOYO\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"LRC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"LLT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"TRX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"FID\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"SNGLS\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"STRAT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"BQX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"FUN\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"KNC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"CDT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"XVG\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"IOTA\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"SNM\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"LINK\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"CVC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"TNT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"REP\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"CTR\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"MDA\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"MTL\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"SALT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"NULS\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"SUB\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"STX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"MTH\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"CAT\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ADX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"PIX\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ETC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ENG\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"},{\"a\":\"ZEC\",\"f\":\"0.00000000\",\"l\":\"0.00000000\"}]}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      UserDataUpdateEvent userDataUpdateEvent = mapper.readValue(accountUpdateJson, UserDataUpdateEvent.class);
      assertEquals(userDataUpdateEvent.eventType.eventTypeId, "outboundAccountInfo");
      assertEquals(userDataUpdateEvent.eventTime, 1L);
      Optional<AccountUpdateEvent> accountUpdateEventOpt = userDataUpdateEvent.accountUpdateEvent;
      accountUpdateEventOpt.ifPresent(accountUpdateEvent -> {
        for (AssetBalance assetBalance : accountUpdateEvent.balances) {
          if ("ETH".equals(assetBalance.asset)) {
            assertEquals(assetBalance.free, "0.10000000");
          } else {
            assertEquals(assetBalance.free, "0.00000000");
          }
          assertEquals(assetBalance.locked, "0.00000000");
        }
      });
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void testOrderUpdateEventDeserializer() {
    final String orderUpdateEventJson = "{\"e\":\"executionReport\",\"E\":1,\"s\":\"NEOETH\",\"c\":\"XXX\",\"S\":\"BUY\",\"o\":\"LIMIT\",\"f\":\"GTC\",\"q\":\"1000.00000000\",\"p\":\"0.00010000\",\"P\":\"0.00000000\",\"F\":\"0.00000000\",\"g\":-1,\"C\":\"5yairWLqfzbusOUdPyG712\",\"x\":\"CANCELED\",\"X\":\"CANCELED\",\"r\":\"NONE\",\"i\":123456,\"l\":\"0.00000000\",\"z\":\"0.00000000\",\"L\":\"0.00000000\",\"n\":\"0\",\"N\":null,\"T\":1,\"t\":-1,\"I\":1,\"w\":false,\"m\":false,\"M\":false}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      UserDataUpdateEvent userDataUpdateEvent = mapper.readValue(orderUpdateEventJson, UserDataUpdateEvent.class);
      assertEquals(userDataUpdateEvent.eventType.eventTypeId, "executionReport");
      assertEquals(userDataUpdateEvent.eventTime, 1L);

      Optional<OrderTradeUpdateEvent> orderTradeUpdateEventOpt = userDataUpdateEvent.orderTradeUpdateEvent;
      orderTradeUpdateEventOpt.ifPresent(orderTradeUpdateEvent -> {
        assertEquals(orderTradeUpdateEvent.symbol, "NEOETH");
        assertEquals(orderTradeUpdateEvent.newClientOrderId, "XXX");

        assertEquals(orderTradeUpdateEvent.side, OrderSide.BUY);
        assertEquals(orderTradeUpdateEvent.type, OrderType.LIMIT);
        assertEquals(orderTradeUpdateEvent.timeInForce, TimeInForce.GTC);

        assertEquals(orderTradeUpdateEvent.originalQuantity, "1000.00000000");
        assertEquals(orderTradeUpdateEvent.price, "0.00010000");

        assertEquals(orderTradeUpdateEvent.executionType, ExecutionType.CANCELED);
        assertEquals(orderTradeUpdateEvent.orderStatus, OrderStatus.CANCELED);
        assertEquals(orderTradeUpdateEvent.orderRejectReason, OrderRejectReason.NONE);

        assertEquals(orderTradeUpdateEvent.orderId, Long.valueOf(123456L));
        assertEquals(orderTradeUpdateEvent.orderTradeTime, Long.valueOf(1L));
      });
    } catch (IOException e) {
      fail();
    }
  }

}

