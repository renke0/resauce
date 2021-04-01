package com.renke.resauce.integration.wiremock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;

public final class ExtendedWiremock {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public static ResponseDefinitionBuilder okJson(Object value) {
    return WireMock.okJson(objectMapper.writeValueAsString(value));
  }
}
