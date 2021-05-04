package com.renke.resauce.model.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DecimalNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.renke.resauce.ResauceTest;
import com.renke.resauce.model.Rolls;
import com.renke.resauce.model.RollsType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RollsDeserializerTest extends ResauceTest {
  @InjectMocks
  RollsDeserializer rollsDeserializer;

  @Nested
  @DisplayName("deserialize")
  class DeserializeTest {

    @Test
    @DisplayName("Should build rolls with absolute value if receives an integer")
    void testDeserializeInteger() throws IOException {
      var parser = mock(JsonParser.class);
      when(parser.hasToken(JsonToken.VALUE_NUMBER_INT)).thenReturn(true);
      when(parser.getIntValue()).thenReturn(5);

      var expected = Rolls.builder().absolute(5).build();

      var actual = rollsDeserializer.deserialize(parser, mock(DeserializationContext.class));

      assertEquals(expected, actual);
      verify(parser, never()).readValueAsTree();
    }

    @Test
    @DisplayName("Should build rolls with node values if receives an object")
    void testDeserializeObject() throws IOException {
      var parser = mock(JsonParser.class);
      var node = new ObjectNode(JsonNodeFactory.instance, Map.of(
          "min", new DecimalNode(BigDecimal.ZERO),
          "max", new DecimalNode(BigDecimal.TEN),
          "type", new TextNode("minecraft:binomial")
      ));

      when(parser.hasToken(JsonToken.VALUE_NUMBER_INT)).thenReturn(false);
      when(parser.readValueAsTree()).thenReturn(node);
      when(parser.getCodec()).thenReturn(new ObjectMapper());

      var expected = Rolls.builder()
          .min(BigDecimal.ZERO)
          .max(BigDecimal.TEN)
          .type(RollsType.BINOMIAL)
          .build();

      var actual = rollsDeserializer.deserialize(parser, mock(DeserializationContext.class));

      assertEquals(expected, actual);
    }
  }
}
