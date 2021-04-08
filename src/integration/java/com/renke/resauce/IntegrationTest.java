package com.renke.resauce;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.renke.resauce.integration.object.ObjectCreator;
import com.renke.resauce.integration.restassured.RestAssuredProvider;
import lombok.experimental.Delegate;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@Tag("integration")
@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {
  public static final String EXCLUSION_IDS = "exclusion-ids";

  @Autowired
  @Delegate
  private WireMockServer wireMockServer;
  @Autowired
  @Delegate
  private RestAssuredProvider restAssuredProvider;
  @Autowired
  @Delegate
  private ObjectCreator objectCreator;

  public void doTimes(int times, Runnable runnable) {
    for (int i = 0; i < times; i++) {
      runnable.run();
    }
  }
}
