package com.binance.api.client.domain.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Custom deserializer for a candlestick stream event, since the structure of the candlestick json differ from the one in the REST API.
 *
 * @see CandlestickEvent
 */
public class CandlestickEventDeserializer extends JsonDeserializer<CandlestickEvent> {

  @Override
  public CandlestickEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);

    // Parse candlestick data
    JsonNode candlestickNode = node.get("k");

    return new CandlestickEvent(
        node.get("e").asText(),
        node.get("E").asLong(),
        node.get("s").asText(),
        candlestickNode.get("t").asLong(),
        candlestickNode.get("o").asText(),
        candlestickNode.get("h").asText(),
        candlestickNode.get("l").asText(),
        candlestickNode.get("c").asText(),
        candlestickNode.get("v").asText(),
        candlestickNode.get("T").asLong(),
        candlestickNode.get("i").asText(),
        candlestickNode.get("f").asLong(),
        candlestickNode.get("L").asLong(),
        candlestickNode.get("q").asText(),
        candlestickNode.get("n").asLong(),
        candlestickNode.get("V").asText(),
        candlestickNode.get("Q").asText(),
        candlestickNode.get("x").asBoolean()
    );
  }
}