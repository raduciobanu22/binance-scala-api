package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.AssetBalance;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Account update event which will reflect the current position/balances of the account.
 *
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUpdateEvent {

  @JsonCreator
  public AccountUpdateEvent(@JsonProperty("e") String eventType,
                            @JsonProperty("E") long eventTime,
                            @JsonDeserialize(contentUsing = AssetBalanceDeserializer.class) @JsonProperty("B") List<AssetBalance> balances) {
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.balances = balances;
  }

  private final String eventType;
  private final long eventTime;
  private final List<AssetBalance> balances;

  public String getEventType() {
    return eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public List<AssetBalance> getBalances() {
    return balances;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime)
        .append("balances", balances)
        .toString();
  }
}
