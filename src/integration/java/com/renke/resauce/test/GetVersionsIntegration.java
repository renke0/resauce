package com.renke.resauce.test;

import com.renke.resauce.IntegrationTest;
import com.renke.resauce.integration.matcher.PropertyMatcher;
import com.renke.resauce.integration.object.DynamicObject;
import java.util.function.Predicate;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.renke.resauce.integration.matcher.ListMatcher.allMatch;
import static com.renke.resauce.integration.matcher.PropertyMatchDescriptor.matching;
import static com.renke.resauce.integration.object.MojangConfigurator.VERSION_RESPONSE_ALIAS;
import static com.renke.resauce.integration.wiremock.ExtendedWiremock.okJson;

@Log4j2
@DisplayName("GET /versions")
public class GetVersionsIntegration extends IntegrationTest {

  @Test
  @DisplayName("Should list all versions")
  void testListAll() {
    var versionResponse = randomObject(VERSION_RESPONSE_ALIAS);

    // @formatter:off
    given(() -> stubFor(
        get("/mc/game/version_manifest_v2.json")
            .willReturn(okJson(versionResponse))))
    .when()
        .get("/versions")
    .then()
        .assertThat().statusCode(200)
        .and().body("[0]", versionMatcher(versionResponse.nested("versions[0]")))
        .and().body("[1]", versionMatcher(versionResponse.nested("versions[1]")))
        .and().body("[2]", versionMatcher(versionResponse.nested("versions[2]")))
        .and().body("[3]", versionMatcher(versionResponse.nested("versions[3]")))
        .and().body("[4]", versionMatcher(versionResponse.nested("versions[4]")))
        .and().body("", allMatch(verifyLatest(versionResponse.nested("latest"))));
    // @formatter:on
  }

  private Matcher<?> versionMatcher(DynamicObject expected) {
    return PropertyMatcher.builder()
        .expectedObject(expected)
        .descriptor(matching("id"))
        .descriptor(matching("type"))
        .descriptor(matching("sha1"))
        .build();
  }

  private Predicate<DynamicObject> verifyLatest(DynamicObject latest) {
    return dynamicObject -> {
      var latestSnapshot = latest.nested("snapshot");
      var latestRelease = latest.nested("release");
      String id = dynamicObject.nested("id");
      boolean isLatest = dynamicObject.nested("latest");
      return (id.equals(latestRelease) || id.equals(latestSnapshot)) == isLatest;
    };
  }
}
