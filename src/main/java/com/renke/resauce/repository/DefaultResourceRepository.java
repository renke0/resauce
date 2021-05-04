package com.renke.resauce.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
@RequiredArgsConstructor
public class DefaultResourceRepository implements ResourceRepository {
  private final ResourcePatternResolver resolver;

  @Override
  public Set<String> resolveVersions() {
    var resources = getResources("classpath*:/game-data/**");
    return Arrays.stream(resources)
        .map(this::asFile)
        .filter(File::isDirectory)
        .map(File::getName)
        .filter(name -> name.matches("\\d+\\.\\d+"))
        .collect(Collectors.toSet());
  }

  @Override
  public String itemList(String versionId) {
    var resource = resolver.getResource("classpath:game-data/" + versionId + "/items.json");
    return loadResource(resource);
  }

  @Override
  public Set<String> loadRecipes(String versionId) {
    return loadResources("classpath*:/game-data/" + versionId + "/recipes/**");
  }

  @Override
  public Set<String> loadLootTables(String versionId) {
    return loadResources("classpath*:/game-data/" + versionId + "/loot_tables/**");
  }

  private File asFile(Resource resource) {
    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private Set<String> loadResources(String path) {
    return Arrays.stream(getResources(path))
        .map(this::loadResource)
        .collect(Collectors.toSet());
  }

  private String loadResource(Resource resource) {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private Resource[] getResources(String path) {
    try {
      return resolver.getResources(path);
    } catch (IOException e) {
      throw new UncheckedIOException("Could not list resources", e);
    }
  }
}
