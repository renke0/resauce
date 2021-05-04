package com.renke.resauce.common.parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class JsonParser {
  private final ObjectMapper objectMapper;

  public JsonParser(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper.copy()
        .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  public <T> T parseJson(String json, TypeReference<T> typeReference) {
    try {
      return objectMapper.readValue(json, typeReference);
    } catch (JsonProcessingException e) {
      throw new ParsingException("Could not parse json value", e);
    }
  }

  public <T> T parseJson(String json, Class<T> type) {
    try {
      return objectMapper.readValue(json, type);
    } catch (JsonProcessingException e) {
      throw new ParsingException("Could not parse json value", e);
    }
  }
}

