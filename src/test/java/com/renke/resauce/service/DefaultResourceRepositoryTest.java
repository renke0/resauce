package com.renke.resauce.service;

import com.renke.resauce.ResauceTest;
import com.renke.resauce.repository.DefaultResourceRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class DefaultResourceRepositoryTest extends ResauceTest {
  @Mock
  ResourcePatternResolver resolver;
  @InjectMocks
  DefaultResourceRepository repository;

  @Nested
  @DisplayName("resolveVersions")
  class ResolveVersionsTest {
    @Test
    @DisplayName("Should load versions based on directory structure")
    void testResolveVersions() throws IOException {
      var resources = mockResources("1.15/", "1.16/");
      when(resolver.getResources(anyString())).thenReturn(resources);

      var actual = repository.resolveVersions();

      assertEquals(Set.of("1.16", "1.15"), actual);
    }

    @Test
    @DisplayName("Should throw exception is IO error happens in the resource listing")
    void testResolveVersionsThrowExceptionOnResourceLoading() throws IOException {
      when(resolver.getResources(anyString())).thenThrow(IOException.class);

      assertThrows(UncheckedIOException.class, () -> repository.resolveVersions());
    }

    @Test
    @DisplayName("Should throw exception is IO error happens in the file resolving")
    void testResolveVersionsThrowExceptionOnFileResolving() throws IOException {
      var resource = mock(Resource.class);
      when(resource.getFile()).thenThrow(IOException.class);
      when(resolver.getResources(anyString())).thenReturn(new Resource[]{resource});

      assertThrows(UncheckedIOException.class, () -> repository.resolveVersions());
    }
  }

  @Nested
  @DisplayName("itemList")
  class ItemListTest {
    @Test
    @DisplayName("Should load the corresponding item list file as string")
    void testItemList() {
      try (var fileCopyUtils = mockStatic(FileCopyUtils.class)) {
        var resource = mockResource("items.json");
        when(resolver.getResource(anyString())).thenReturn(resource);

        repository.itemList("1.1");

        verify(resolver).getResource("classpath:game-data/1.1/items.json");
        fileCopyUtils.verify(() -> FileCopyUtils.copyToString(any(Reader.class)));
      }
    }

    @Test
    @DisplayName("Should throw an IO error if the resource loading fails")
    void testItemListFailsToRead() throws IOException {
      var resource = mockResource("items.json");
      when(resolver.getResource(anyString())).thenReturn(resource);
      when(resource.getInputStream()).thenThrow(new IOException());

      assertThrows(UncheckedIOException.class, () -> repository.itemList("1.1"));
    }
  }

  @Nested
  @DisplayName("loadRecipes")
  class LoadRecipesTest {
    @Test
    @DisplayName("Should load recipes as string arrays")
    void testLoadRecipes() throws IOException {
      try (var fileCopyUtils = mockStatic(FileCopyUtils.class)) {
        var resources = mockResources("recipe1.json", "recipe2.json", "recipe3.json");
        when(resolver.getResources(anyString())).thenReturn(resources);
        fileCopyUtils.when(() -> FileCopyUtils.copyToString(any(Reader.class))).thenReturn("1", "2", "3");

        var actual = repository.loadRecipes("1.1");
        verify(resolver).getResources("classpath*:/game-data/1.1/recipes/**");
        assertEquals(Set.of("1", "2", "3"), actual);
      }
    }

    @Test
    @DisplayName("Should throw IO exception if an error in the file loading happens")
    void testLoadRecipesThrowIOException() throws IOException {
      when(resolver.getResources(anyString())).thenThrow(IOException.class);

      assertThrows(UncheckedIOException.class, () -> repository.loadRecipes("1.1"));
    }
  }

  @Nested
  @DisplayName("loadLootTables")
  class LoadLootTablesTest {
    @Test
    @DisplayName("Should load loot tables as string arrays")
    void testLoadRecipes() throws IOException {
      try (var fileCopyUtils = mockStatic(FileCopyUtils.class)) {
        var resources = mockResources("loot_table1.json", "loot_table2.json", "loot_table3.json");
        when(resolver.getResources(anyString())).thenReturn(resources);
        fileCopyUtils.when(() -> FileCopyUtils.copyToString(any(Reader.class))).thenReturn("1", "2", "3");

        var actual = repository.loadLootTables("1.1");
        verify(resolver).getResources("classpath*:/game-data/1.1/loot_tables/**");
        assertEquals(Set.of("1", "2", "3"), actual);
      }
    }

    @Test
    @DisplayName("Should throw IO exception if an error in the file loading happens")
    void testLoadLootTablesThrowIOException() throws IOException {
      when(resolver.getResources(anyString())).thenThrow(IOException.class);

      assertThrows(UncheckedIOException.class, () -> repository.loadLootTables("1.1"));
    }
  }

  Resource[] mockResources(String... paths) {
    return Arrays.stream(paths)
        .map(this::mockResource)
        .toArray(Resource[]::new);
  }

  Resource mockResource(String path) {
    var tmpDir = System.getProperty("java.io.tmpdir") + "com.renke.resauce/test";
    var directory = new File(tmpDir);
    directory.mkdirs();
    var resourceFile = new File(directory, path);
    try {
      if (path.endsWith("/")) {
        resourceFile.mkdirs();
      } else if (!resourceFile.exists()) {
        resourceFile.createNewFile();
      }

      var resource = mock(Resource.class, withSettings().lenient());
      when(resource.getFile()).thenReturn(resourceFile);
      when(resource.getInputStream()).thenReturn(mock(InputStream.class));
      return resource;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
