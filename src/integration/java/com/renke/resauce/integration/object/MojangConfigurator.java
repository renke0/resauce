package com.renke.resauce.integration.object;

import com.renke.resauce.model.mojang.LatestVersionModel;
import com.renke.resauce.model.mojang.VersionModel;
import com.renke.resauce.model.mojang.VersionResponseModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.renke.resauce.integration.object.ObjectCreatorConfigurator.field;
import static com.renke.resauce.integration.object.RegexGenerator.HEXA_REGEX;
import static com.renke.resauce.integration.object.RegexGenerator.URL_REGEX;
import static com.renke.resauce.integration.object.RegexGenerator.VERSION_ID_REGEX;
import static com.renke.resauce.model.mojang.VersionTypeModel.RELEASE;
import static com.renke.resauce.model.mojang.VersionTypeModel.SNAPSHOT;

@Configuration
public class MojangConfigurator {
  public static final String VERSION_ALIAS = "Version";
  public static final String VERSION_RESPONSE_ALIAS = "VersionResponse";

  @Bean
  ObjectCreatorConfigurator versionConfigurator() {
    return objectCreator ->
        objectCreator.addAlias(VERSION_ALIAS, VersionModel.class)
            .getParameters()
            .randomize(field(VersionModel.class, "id"), new RegexGenerator(VERSION_ID_REGEX))
            .randomize(field(VersionModel.class, "url"), new RegexGenerator(URL_REGEX))
            .randomize(field(VersionModel.class, "sha1"), new RegexGenerator(HEXA_REGEX));
  }

  @Bean
  ObjectCreatorConfigurator versionResponseConfigurator() {
    return objectCreator ->
        objectCreator.addAlias(VERSION_RESPONSE_ALIAS, VersionResponseModel.class)
            .addPostProcessor(VERSION_RESPONSE_ALIAS, (VersionResponseModel v) -> {
              var release = v.getVersions().get(0);
              release.setType(RELEASE);
              var snapshot = v.getVersions().get(1);
              snapshot.setType(SNAPSHOT);
              v.latest(
                  new LatestVersionModel()
                      .release(release.getId())
                      .snapshot(snapshot.getId()));
            })
            .getParameters()
            .excludeField(field(VersionResponseModel.class, "latest"));
  }
}
