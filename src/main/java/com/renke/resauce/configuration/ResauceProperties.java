package com.renke.resauce.configuration;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "resauce")
@Data
public class ResauceProperties {
  private MojangProperties mojang;
  private IntegrationProperties integration;

  @Data
  public static class MojangProperties {
    @NotBlank
    private String url;
  }

  @Data
  public static class IntegrationProperties {
    private WireMockProperties wireMock;

    @Data
    public static class WireMockProperties {
      private Integer port;
    }
  }
}
