package com.binance.api.domain.market;

import com.binance.api.client.domain.market.Candlestick;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the adequate deserialization of candlestick JSON information.
 */
public class CandlestickDeserializerTest {

  @Test
  public void testCandlestickDeserializerTest() {
    final String candlestickJson = "[\n" +
        "    1499040000000,\n" +
        "        \"0.01634790\",\n" +
        "        \"0.80000000\",\n" +
        "        \"0.01575800\",\n" +
        "        \"0.01577100\",\n" +
        "        \"148976.11427815\",\n" +
        "        1499644799999,\n" +
        "        \"2434.19055334\",\n" +
        "        308,\n" +
        "        \"1756.87402397\",\n" +
        "        \"28.46694368\",\n" +
        "        \"17928899.62484339\"\n" +
        "        ]";
    ObjectMapper mapper = new ObjectMapper();
    try {
      Candlestick candlestick = mapper.readValue(candlestickJson, Candlestick.class);
      assertEquals((long)candlestick.openTime, 1499040000000L);
      assertEquals(candlestick.open, "0.01634790");
      assertEquals(candlestick.high, "0.80000000");
      assertEquals(candlestick.low, "0.01575800");
      assertEquals(candlestick.close, "0.01577100");
      assertEquals(candlestick.volume, "148976.11427815");
      assertEquals((long)candlestick.closeTime, 1499644799999L);
      assertEquals(candlestick.quoteAssetVolume, "2434.19055334");
      assertEquals((long)candlestick.numberOfTrades, 308L);
      assertEquals(candlestick.takerBuyBaseAssetVolume, "1756.87402397");
      assertEquals(candlestick.takerBuyQuoteAssetVolume, "28.46694368");
    } catch (IOException e) {
      fail();
    }
  }
}
