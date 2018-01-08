package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

/**
 * User data update event which can be of two types:
 *
 * 1) outboundAccountInfo, whenever there is a change in the account (e.g. balance of an asset)
 * 2) executionReport, whenever there is a trade or an order
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserDataUpdateEventDeserializer.class)
public class UserDataUpdateEvent {

  private final UserDataUpdateEventType eventType;

  private final long eventTime;

  private final Optional<AccountUpdateEvent> accountUpdateEvent;

  private final Optional<OrderTradeUpdateEvent> orderTradeUpdateEvent;

  public UserDataUpdateEvent(UserDataUpdateEventType eventType, long eventTime, Optional<AccountUpdateEvent> accountUpdateEvent, Optional<OrderTradeUpdateEvent> orderTradeUpdateEvent) {
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.accountUpdateEvent = accountUpdateEvent;
    this.orderTradeUpdateEvent = orderTradeUpdateEvent;
  }

  public UserDataUpdateEventType getEventType() {
    return eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public Optional<AccountUpdateEvent> getAccountUpdateEvent() {
    return accountUpdateEvent;
  }

  public Optional<OrderTradeUpdateEvent> getOrderTradeUpdateEvent() {
    return orderTradeUpdateEvent;
  }

  @Override
  public String toString() {
    ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime);
    if (eventType == UserDataUpdateEventType.ACCOUNT_UPDATE) {
      sb.append("accountUpdateEvent", accountUpdateEvent);
    } else {
      sb.append("orderTradeUpdateEvent", orderTradeUpdateEvent);
    }
    return sb.toString();
  }

  public enum UserDataUpdateEventType {
    ACCOUNT_UPDATE("outboundAccountInfo"),
    ORDER_TRADE_UPDATE("executionReport");

    private final String eventTypeId;

    UserDataUpdateEventType(String eventTypeId) {
      this.eventTypeId = eventTypeId;
    }

    public String getEventTypeId() {
      return eventTypeId;
    }

    public static UserDataUpdateEventType fromEventTypeId(String eventTypeId) {
      if (ACCOUNT_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ACCOUNT_UPDATE;
      } else if (ORDER_TRADE_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ORDER_TRADE_UPDATE;
      }
      throw new IllegalArgumentException("Unrecognized user data update event type id: " + eventTypeId);
    }
  }
}
