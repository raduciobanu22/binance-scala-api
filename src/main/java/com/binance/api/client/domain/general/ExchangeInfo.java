package com.binance.api.client.domain.general;

import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Current exchange trading rules and symbol information.
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfo {

  public final String timezone;

  public final Long serverTime;

  public final List<RateLimit> rateLimits;

  // public final List<String> exchangeFilters;

  public final List<SymbolInfo> symbols;

  @JsonCreator
  public ExchangeInfo(@JsonProperty("timezone") String timezone,
                      @JsonProperty("serverTime") Long serverTime,
                      @JsonProperty("rateLimits") List<RateLimit> rateLimits,
                      @JsonProperty("symbols") List<SymbolInfo> symbols) {
    this.timezone = timezone;
    this.serverTime = serverTime;
    this.rateLimits = rateLimits;
    this.symbols = symbols;
  }

  /**
   * @param symbol the symbol to obtain information for (e.g. ETHBTC)
   * @return symbol exchange information
   */
  public SymbolInfo getSymbolInfo(String symbol) {
    return symbols.stream().filter(symbolInfo -> symbolInfo.symbol.equals(symbol))
        .findFirst()
        .orElseThrow(() -> new BinanceApiException("Unable to obtain information for symbol " + symbol));
  }

}
