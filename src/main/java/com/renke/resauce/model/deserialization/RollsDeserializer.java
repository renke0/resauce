package com.renke.resauce.model.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.renke.resauce.model.Rolls;
import com.renke.resauce.model.RollsType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class RollsDeserializer extends JsonDeserializer<Rolls> {
  @Override
  public Rolls deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
      return Rolls.builder()
          .absolute(parser.getIntValue())
          .build();
    }
    JsonNode node = parser.readValueAsTree();
    return Rolls.builder()
        .min(asBigDecimal(node.get("min")))
        .max(asBigDecimal(node.get("max")))
        .type(parser.getCodec().treeToValue(node.get("type"), RollsType.class))
        .build();
  }

  private BigDecimal asBigDecimal(JsonNode node) {
    return Optional.ofNullable(node)
        .map(JsonNode::asText)
        .map(BigDecimal::new)
        .orElse(null);
  }
}
