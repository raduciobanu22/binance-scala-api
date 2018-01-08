package com.binance.api.client.domain.market;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Custom deserializer for an Candlestick, since the API returns an array in the format [ price, qty, [] ].
 */
public class CandlestickDeserializer extends JsonDeserializer<Candlestick> {

  @Override
  public Candlestick deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);

    return new Candlestick(
        node.get(0).asLong(),
        node.get(1).asText(),
        node.get(2).asText(),
        node.get(3).asText(),
        node.get(4).asText(),
        node.get(5).asText(),
        node.get(6).asLong(),
        node.get(7).asText(),
        node.get(8).asLong(),
        node.get(9).asText(),
        node.get(10).asText()
    );
  }
}
