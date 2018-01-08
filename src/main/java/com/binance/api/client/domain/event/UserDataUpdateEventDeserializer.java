package com.binance.api.client.domain.event;

import com.binance.api.client.domain.event.UserDataUpdateEvent.UserDataUpdateEventType;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

/**
 * Custom deserializer for a User Data stream event, since the API can return two different responses in this stream.
 * @see UserDataUpdateEvent
 */
public class UserDataUpdateEventDeserializer extends JsonDeserializer<UserDataUpdateEvent> {

  @Override
  public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    String json = node.toString();

    UserDataUpdateEventType type = UserDataUpdateEventType.fromEventTypeId(node.get("e").asText());

    return new UserDataUpdateEvent(
        type,
        node.get("E").asLong(),
        accountUpdateEventOpt(type, json),
        orderTradeUpdateEventOpt(type, json)
      );
  }

  static Optional<AccountUpdateEvent> accountUpdateEventOpt(UserDataUpdateEventType type, String json) {
    if (type == UserDataUpdateEventType.ACCOUNT_UPDATE) {
      return Optional.of(getUserDataUpdateEventDetail(json, AccountUpdateEvent.class));
    }
    return Optional.empty();
  }

  static Optional<OrderTradeUpdateEvent> orderTradeUpdateEventOpt(UserDataUpdateEventType type, String json) {
    if (type != UserDataUpdateEventType.ACCOUNT_UPDATE) {
      return Optional.of(getUserDataUpdateEventDetail(json, OrderTradeUpdateEvent.class));
    }
    return Optional.empty();
  }

  public static <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }
}