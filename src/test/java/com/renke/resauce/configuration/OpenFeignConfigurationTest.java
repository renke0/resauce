package com.renke.resauce.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renke.resauce.ResauceTest;
import feign.okhttp.OkHttpClient;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class OpenFeignConfigurationTest extends ResauceTest {

  @InjectMocks
  OpenFeignConfiguration configuration;

  @Nested
  @DisplayName("Client client()")
  class ClientTest {
    @Test
    @DisplayName("Should provide an OkHttpClient")
    void testClient() {
      var client = configuration.client();
      assertTrue(client instanceof OkHttpClient);
    }
  }

  @Nested
  @DisplayName("Decoder decoder(ObjectMapper)")
  class DecoderTest {
    @Test
    @DisplayName("Should provide a ResponseEntityDecoder")
    void testDecoder() {
      var decoder = configuration.decoder(mock(ObjectMapper.class));
      assertTrue(decoder instanceof ResponseEntityDecoder);
    }

    @Test
    @DisplayName("Should wrap a SpringEncoder within the actual encoder")
    void testDecoder_wrapSpringEncoder() {
      var arguments = new ArrayList<>();
      try (var ignored = mockConstruction(SpringDecoder.class,
          (mock, context) -> arguments.addAll(context.arguments()))) {
        configuration.decoder(mock(ObjectMapper.class));
        assertEquals(1, arguments.size());
        assertTrue(arguments.get(0) instanceof ObjectFactory);
        assertTrue(((ObjectFactory<?>) arguments.get(0)).getObject() instanceof HttpMessageConverters);
      }
    }
  }
}
