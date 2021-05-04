package com.renke.resauce.service;

import com.renke.resauce.ResauceTest;
import com.renke.resauce.client.MojangClient;
import com.renke.resauce.mapper.MojangVersionToGameVersionMapper;
import com.renke.resauce.model.base.GameVersionModel;
import com.renke.resauce.model.mojang.LatestVersionModel;
import com.renke.resauce.model.mojang.VersionResponseModel;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.renke.resauce.model.base.VersionTypeModel.OLD_ALPHA;
import static com.renke.resauce.model.base.VersionTypeModel.OLD_BETA;
import static com.renke.resauce.model.base.VersionTypeModel.RELEASE;
import static com.renke.resauce.model.base.VersionTypeModel.SNAPSHOT;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;

class DefaultGameVersionServiceTest extends ResauceTest {
  @Mock
  MojangClient mojangClient;
  @Mock
  MojangVersionToGameVersionMapper versionMapper;
  @InjectMocks
  DefaultGameVersionService service;

  @Nested
  @DisplayName("listGameVersions")
  class ListGameVersionsTest {

    @Test
    @DisplayName("Should list all the game versions when no types are specified")
    void testListGameVersionsNoFilter() {
      when(mojangClient.getVersions())
          .thenReturn(ok(nextObject(VersionResponseModel.class)));
      when(versionMapper.toGameVersion(any()))
          .thenReturn(nextObject(GameVersionModel.class));

      var actual = service.listGameVersions(emptyList());

      verify(versionMapper, times(DEFAULT_COLLECTION_SIZE)).toGameVersion(any());
      assertEquals(DEFAULT_COLLECTION_SIZE, actual.size());
    }

    @Test
    @DisplayName("Should filter the game versions when types are specified")
    void testListGameVersionsFiltering() {
      var filterVersions = List.of(SNAPSHOT, RELEASE);
      var gameVersions = Stream.of(SNAPSHOT, RELEASE, OLD_ALPHA, SNAPSHOT, OLD_BETA)
          .map(t -> nextObject(GameVersionModel.class).type(t))
          .toArray(GameVersionModel[]::new);

      when(versionMapper.toGameVersion(any()))
          .thenReturn(first(gameVersions), remaining(gameVersions));
      when(mojangClient.getVersions())
          .thenReturn(ok(nextObject(VersionResponseModel.class)));

      var actual = service.listGameVersions(filterVersions);

      verify(versionMapper, times(DEFAULT_COLLECTION_SIZE)).toGameVersion(any());
      assertEquals(3, actual.size());
      actual.forEach(v -> assertTrue(filterVersions.contains(v.getType())));
    }

    @Test
    @DisplayName("Should assign the correct 'latest' flag")
    void testListGameVersionsLatest() {
      var gameVersions = Stream.of("snapshot-id", "release-id", "other-id")
          .map(id -> nextObject(GameVersionModel.class).id(id))
          .toArray(GameVersionModel[]::new);

      when(versionMapper.toGameVersion(any()))
          .thenReturn(first(gameVersions), remaining(gameVersions));
      when(mojangClient.getVersions())
          .thenReturn(ok(nextObject(VersionResponseModel.class).latest(
              new LatestVersionModel().release("release-id").snapshot("snapshot-id"))));

      var actual = service.listGameVersions(emptyList());

      assertTrue(actual.get(0).getLatest());
      assertTrue(actual.get(1).getLatest());
      assertFalse(actual.get(2).getLatest());
    }
  }
}
