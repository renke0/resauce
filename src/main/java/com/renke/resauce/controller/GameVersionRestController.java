package com.renke.resauce.controller;

import com.renke.resauce.api.base.GameVersionApi;
import com.renke.resauce.model.base.GameVersionModel;
import com.renke.resauce.model.base.VersionTypeModel;
import com.renke.resauce.service.GameVersionService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GameVersionRestController implements GameVersionApi {
  private final GameVersionService gameVersionService;

  @Override
  public ResponseEntity<List<GameVersionModel>> getVersions(Optional<List<VersionTypeModel>> types) {
    return ok(gameVersionService.listGameVersions(types.orElseGet(Collections::emptyList)));
  }
}
