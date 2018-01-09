package com.binance.api.domain.general;

import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.general.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test deserialization of exchange information.
 */
public class ExchangeInfoDeserializerTest {

  @Test
  public void testExchangeInfoDeserialization() {
    final String json = "{\n" +
        "  \"timezone\": \"UTC\",\n" +
        "  \"serverTime\": 1508631584636,\n" +
        "  \"rateLimits\": [{\n" +
        "      \"rateLimitType\": \"REQUESTS\",\n" +
        "      \"interval\": \"MINUTE\",\n" +
        "      \"limit\": 1200\n" +
        "    },\n" +
        "    {\n" +
        "      \"rateLimitType\": \"ORDERS\",\n" +
        "      \"interval\": \"SECOND\",\n" +
        "      \"limit\": 10\n" +
        "    },\n" +
        "    {\n" +
        "      \"rateLimitType\": \"ORDERS\",\n" +
        "      \"interval\": \"DAY\",\n" +
        "      \"limit\": 100000\n" +
        "    }\n" +
        "  ],\n" +
        "  \"exchangeFilters\": [],\n" +
        "  \"symbols\": [{\n" +
        "    \"symbol\": \"ETHBTC\",\n" +
        "    \"status\": \"TRADING\",\n" +
        "    \"baseAsset\": \"ETH\",\n" +
        "    \"baseAssetPrecision\": 8,\n" +
        "    \"quoteAsset\": \"BTC\",\n" +
        "    \"quotePrecision\": 8,\n" +
        "    \"orderTypes\": [\"LIMIT\", \"MARKET\"],\n" +
        "    \"icebergAllowed\": false,\n" +
        "    \"filters\": [{\n" +
        "      \"filterType\": \"PRICE_FILTER\",\n" +
        "      \"minPrice\": \"0.00000100\",\n" +
        "      \"maxPrice\": \"100000.00000000\",\n" +
        "      \"tickSize\": \"0.00000100\"\n" +
        "    }, {\n" +
        "      \"filterType\": \"LOT_SIZE\",\n" +
        "      \"minQty\": \"0.00100000\",\n" +
        "      \"maxQty\": \"100000.00000000\",\n" +
        "      \"stepSize\": \"0.00100000\"\n" +
        "    }, {\n" +
        "      \"filterType\": \"MIN_NOTIONAL\",\n" +
        "      \"minNotional\": \"0.00100000\"\n" +
        "    }]\n" +
        "  }]" +
        "}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      ExchangeInfo exchangeInfo = mapper.readValue(json, ExchangeInfo.class);
      System.out.println(exchangeInfo);
      assertEquals(exchangeInfo.timezone, "UTC");
      assertEquals((long)exchangeInfo.serverTime, 1508631584636L);

      List<RateLimit> rateLimits = exchangeInfo.rateLimits;
      assertEquals(rateLimits.size(), 3);
      testRateLimit(rateLimits.get(0), RateLimitType.REQUESTS, RateLimitInterval.MINUTE, 1200);
      testRateLimit(rateLimits.get(1), RateLimitType.ORDERS, RateLimitInterval.SECOND, 10);
      testRateLimit(rateLimits.get(2), RateLimitType.ORDERS, RateLimitInterval.DAY, 100000);

      List<SymbolInfo> symbols = exchangeInfo.symbols;
      assertEquals(symbols.size(), 1);
      SymbolInfo symbolInfo = symbols.get(0);
      assertEquals(symbolInfo.symbol, "ETHBTC");
      assertEquals(symbolInfo.status, SymbolStatus.TRADING);
      assertEquals(symbolInfo.baseAsset, "ETH");
      assertEquals((int)symbolInfo.baseAssetPrecision, 8);
      assertEquals(symbolInfo.quoteAsset, "BTC");
      assertEquals((int)symbolInfo.quotePrecision, 8);
      assertEquals(symbolInfo.orderTypes, Arrays.asList(OrderType.LIMIT, OrderType.MARKET));
      assertFalse(symbolInfo.icebergAllowed);

      List<SymbolFilter> symbolFilters = symbolInfo.filters;
      assertEquals(symbolFilters.size(), 3);

      SymbolFilter priceFilter = symbolFilters.get(0);
      assertEquals(priceFilter.filterType, FilterType.PRICE_FILTER);
      assertEquals(priceFilter.minPrice, "0.00000100");
      assertEquals(priceFilter.maxPrice, "100000.00000000");
      assertEquals(priceFilter.tickSize, "0.00000100");

      SymbolFilter lotSizeFilter = symbolFilters.get(1);
      assertEquals(lotSizeFilter.filterType, FilterType.LOT_SIZE);
      assertEquals(lotSizeFilter.minQty, "0.00100000");
      assertEquals(lotSizeFilter.maxQty, "100000.00000000");
      assertEquals(lotSizeFilter.stepSize, "0.00100000");

      SymbolFilter minNotionalFilter = symbolFilters.get(2);
      assertEquals(minNotionalFilter.filterType, FilterType.MIN_NOTIONAL);
      assertEquals(minNotionalFilter.minNotional, "0.00100000");
    } catch (IOException e) {
      fail();
    }
  }

  private void testRateLimit(RateLimit rateLimit, RateLimitType expectedRateLimitType, RateLimitInterval expectedInterval, int expectedLimit) {
    assertEquals(rateLimit.rateLimitType, expectedRateLimitType);
    assertEquals(rateLimit.interval, expectedInterval);
    assertEquals((long)rateLimit.limit, expectedLimit);
  }
}
