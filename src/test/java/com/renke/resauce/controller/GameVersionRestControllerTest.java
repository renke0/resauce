package com.renke.resauce.controller;

import com.renke.resauce.ResauceTest;
import com.renke.resauce.service.GameVersionService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.renke.resauce.model.base.VersionTypeModel.RELEASE;
import static com.renke.resauce.model.base.VersionTypeModel.SNAPSHOT;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

class GameVersionRestControllerTest extends ResauceTest {
  @Mock
  GameVersionService gameVersionService;
  @InjectMocks
  GameVersionRestController controller;

  @Nested
  @DisplayName("getVersions")
  class GetVersionsTest {

    @Test
    @DisplayName("should list versions with an empty type list when the optional is empty")
    void testEmptyTypeOptional() {
      controller.getVersions(Optional.empty());
      verify(gameVersionService).listGameVersions(argThat(List::isEmpty));
    }

    @Test
    @DisplayName("should list versions with an empty type list when the list is empty")
    void testEmptyTypeList() {
      controller.getVersions(Optional.of(Collections.emptyList()));
      verify(gameVersionService).listGameVersions(argThat(List::isEmpty));
    }

    @Test
    @DisplayName("should list versions with the given types")
    void testWithTypes() {
      controller.getVersions(Optional.of(List.of(RELEASE, SNAPSHOT)));
      verify(gameVersionService).listGameVersions(List.of(RELEASE, SNAPSHOT));
    }
  }
}
