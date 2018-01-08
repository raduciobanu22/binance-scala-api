package com.binance.api.client.domain.general;

import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Current exchange trading rules and symbol information.
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfo {

  private final String timezone;

  private final Long serverTime;

  private final List<RateLimit> rateLimits;

  // private final List<String> exchangeFilters;

  private final List<SymbolInfo> symbols;

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

  public String getTimezone() {
    return timezone;
  }

  public Long getServerTime() {
    return serverTime;
  }

  public List<RateLimit> getRateLimits() {
    return rateLimits;
  }

  public List<SymbolInfo> getSymbols() {
    return symbols;
  }

  /**
   * @param symbol the symbol to obtain information for (e.g. ETHBTC)
   * @return symbol exchange information
   */
  public SymbolInfo getSymbolInfo(String symbol) {
    return symbols.stream().filter(symbolInfo -> symbolInfo.getSymbol().equals(symbol))
        .findFirst()
        .orElseThrow(() -> new BinanceApiException("Unable to obtain information for symbol " + symbol));
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("timezone", timezone)
        .append("serverTime", serverTime)
        .append("rateLimits", rateLimits)
        .append("symbols", symbols)
        .toString();
  }
}
