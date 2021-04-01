package com.renke.resauce.integration.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestAssuredProvider {
  private final ObjectMapper objectMapper;
  private int port;
  private RestAssuredConfig restAssuredConfig;

  @EventListener
  public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
    port = event.getWebServer().getPort();
    restAssuredConfig = RestAssuredConfig.config()
        .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((type, s) -> objectMapper))
        .decoderConfig(new DecoderConfig());
  }

  public RequestSpecification given() {
    return RestAssured.given(
        new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(port)
            .setBasePath("/api")
            .setContentType("application/json")
            .build()
            .config(restAssuredConfig)
    );
  }

  public RequestSpecification given(Runnable setup) {
    setup.run();
    return given();
  }

  public RequestSpecification when() {
    return given().when();
  }

}
