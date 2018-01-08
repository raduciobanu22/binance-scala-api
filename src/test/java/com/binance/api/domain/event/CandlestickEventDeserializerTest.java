package com.binance.api.domain.event;

import com.binance.api.client.domain.event.CandlestickEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests that JSON responses from ta candlestick event are converted to the appropriate <code>CandlestickEvent</code> object.
 */
public class CandlestickEventDeserializerTest {

  @Test
  public void testCandlestickEventDeserializer() {
    String candlestickEventJson = "{\n" +
        "        \"e\": \"kline\",\n" +
        "        \"E\": 1,\n" +
        "        \"s\": \"ETHBTC\",\n" +
        "        \"k\": {\n" +
        "        \"t\": 1499404860000,\n" +
        "        \"T\": 1499404919999,\n" +
        "        \"s\": \"ETHBTC\", \n" +
        "        \"i\": \"1m\",\n" +
        "        \"f\": 77462, \n" +
        "        \"L\": 77465, \n" +
        "        \"o\": \"0.10278577\", \n" +
        "        \"c\": \"0.10278645\", \n" +
        "        \"h\": \"0.10278712\", \n" +
        "        \"l\": \"0.10278518\", \n" +
        "        \"v\": \"17.47929838\", \n" +
        "        \"n\": 4, \n" +
        "        \"x\": false, \n" +
        "        \"q\": \"1.79662878\", \n" +
        "        \"V\": \"2.34879839\", \n" +
        "        \"Q\": \"0.24142166\", \n" +
        "        \"B\": \"13279784.01349473\"\n" +
        "      }}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      CandlestickEvent candlestickEvent = mapper.readValue(candlestickEventJson, CandlestickEvent.class);
      assertEquals(candlestickEvent.eventType, "kline");
      assertEquals(candlestickEvent.eventTime, 1L);
      assertEquals(candlestickEvent.symbol, "ETHBTC");

      assertEquals((long)candlestickEvent.openTime, 1499404860000L);
      assertEquals(candlestickEvent.open, "0.10278577");
      assertEquals(candlestickEvent.high, "0.10278712");
      assertEquals(candlestickEvent.low, "0.10278518");
      assertEquals(candlestickEvent.close, "0.10278645");
      assertEquals(candlestickEvent.volume, "17.47929838");
      assertEquals((long)candlestickEvent.closeTime, 1499404919999L);
      assertEquals(candlestickEvent.intervalId, "1m");
      assertEquals((long)candlestickEvent.firstTradeId, 77462L);
      assertEquals((long)candlestickEvent.lastTradeId, 77465L);
      assertEquals(candlestickEvent.quoteAssetVolume, "1.79662878");
      assertEquals((long)candlestickEvent.numberOfTrades, 4L);
      assertEquals(candlestickEvent.takerBuyBaseAssetVolume, "2.34879839");
      assertEquals(candlestickEvent.takerBuyQuoteAssetVolume, "0.24142166");
      assertEquals(candlestickEvent.isBarFinal, false);
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }
}
