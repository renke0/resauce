package com.renke.resauce.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renke.resauce.ResauceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

class SerializationConfigurationTest extends ResauceTest {
  @InjectMocks
  SerializationConfiguration configuration;

  @Nested
  @DisplayName("ObjectMapper objectMapper()")
  class ObjectMapperTest {
    @Test
    @DisplayName("Should scan the classpath and register Jackson Modules")
    void testObjectMapper() {
      try (var ignored = mockConstruction(ObjectMapper.class)) {
        var objectMapper = configuration.objectMapper();
        verify(objectMapper).findAndRegisterModules();
      }
    }
  }
}
