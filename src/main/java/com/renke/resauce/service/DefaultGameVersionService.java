package com.renke.resauce.service;

import com.renke.resauce.client.MojangClient;
import com.renke.resauce.mapper.MojangVersionToGameVersionMapper;
import com.renke.resauce.model.base.GameVersionModel;
import com.renke.resauce.model.base.VersionTypeModel;
import com.renke.resauce.model.mojang.LatestVersionModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.renke.resauce.common.functions.Functions.consuming;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class DefaultGameVersionService implements GameVersionService {
  private final MojangClient mojangApi;
  private final MojangVersionToGameVersionMapper versionMapper;

  @Override
  public List<GameVersionModel> listGameVersions(List<VersionTypeModel> types) {
    var versionResponseModel = requireNonNull(mojangApi.getVersions().getBody());
    return versionResponseModel
        .getVersions()
        .stream()
        .map(versionMapper::toGameVersion)
        .filter(v -> types.isEmpty() || types.contains(v.getType()))
        .map(consuming(v -> setLatest(v, versionResponseModel.getLatest())))
        .collect(Collectors.toList());
  }

  private void setLatest(GameVersionModel versionModel, LatestVersionModel latest) {
    versionModel.setLatest(
        versionModel.getId().equals(latest.getSnapshot()) ||
            versionModel.getId().equals(latest.getRelease()));
  }
}
