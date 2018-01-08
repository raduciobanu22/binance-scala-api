package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.AssetBalance;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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

  public final String eventType;
  public final long eventTime;
  public final List<AssetBalance> balances;

}
