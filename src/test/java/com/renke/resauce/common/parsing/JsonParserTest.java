package com.renke.resauce.common.parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renke.resauce.ResauceTest;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JsonParserTest extends ResauceTest {
  @Mock
  ObjectMapper objectMapper;
  JsonParser jsonParser;

  @BeforeEach
  void setup() {
    when(objectMapper.copy()).thenReturn(objectMapper);
    when(objectMapper.configure(any(DeserializationFeature.class), anyBoolean())).thenReturn(objectMapper);
    jsonParser = new JsonParser(objectMapper);
  }

  @Nested
  @DisplayName("parseJson")
  class TestParseJsonWithTypeReference {
    @Test
    @DisplayName("Should delegate the mapping to the wrapped ObjectMapper")
    void testParseJson() throws JsonProcessingException {
      var json = "{\"abc\": \"def\"}";
      var typeReference = new TypeReference<Map<String, String>>() {};

      jsonParser.parseJson(json, typeReference);

      verify(objectMapper).readValue(json, typeReference);
    }

    @Test
    @DisplayName("Should throw a ParsingException if the ObjectMapper fails to read the value")
    @SuppressWarnings("unchecked")
    void testParseJsonFail() throws JsonProcessingException {
      var json = "{\"abc\": \"def\", \"malformed\"}";
      var typeReference = new TypeReference<Map<String, String>>() {};
      when(objectMapper.readValue(anyString(), any(TypeReference.class))).thenThrow(JsonProcessingException.class);

      assertThrows(ParsingException.class, () -> jsonParser.parseJson(json, typeReference));
    }
  }


  @Nested
  @DisplayName("parseJson")
  class TestParseJsonWithClass {
    @Test
    @DisplayName("Should delegate the mapping to the wrapped ObjectMapper")
    void testParseJson() throws JsonProcessingException {
      var json = "{\"abc\": \"def\"}";
      var type = Map.class;

      jsonParser.parseJson(json, type);

      verify(objectMapper).readValue(json, type);
    }

    @Test
    @DisplayName("Should throw a ParsingException if the ObjectMapper fails to read the value")
    @SuppressWarnings("unchecked")
    void testParseJsonFail() throws JsonProcessingException {
      var json = "{\"abc\": \"def\", \"malformed\"}";
      var type = Map.class;
      when(objectMapper.readValue(anyString(), any(Class.class))).thenThrow(JsonProcessingException.class);

      assertThrows(ParsingException.class, () -> jsonParser.parseJson(json, type));
    }
  }
}
