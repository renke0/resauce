package com.renke.resauce.integration.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.renke.resauce.configuration.ResauceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

@Configuration
@Slf4j
public class WireMockConfiguration {

  @Bean
  public WireMockServer wireMockServer(
      ConfigurableApplicationContext applicationContext,
      ResauceProperties properties) {
    var wireMockServer = new WireMockServer(
        new com.github.tomakehurst.wiremock.core.WireMockConfiguration()
            .port(properties.getIntegration().getWireMock().getPort()));
    log.info("Starting WireMock server");
    wireMockServer.start();

    applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

    applicationContext.addApplicationListener(applicationEvent -> {
      if (applicationEvent instanceof ContextClosedEvent) {
        log.info("Stopping WireMock server");
        wireMockServer.stop();
      }
    });
    return wireMockServer;
  }
}

