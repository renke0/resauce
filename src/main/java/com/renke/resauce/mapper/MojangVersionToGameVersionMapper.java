package com.renke.resauce.mapper;

import com.renke.resauce.model.base.GameVersionModel;
import com.renke.resauce.model.mojang.VersionModel;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfiguration.class)
public interface MojangVersionToGameVersionMapper {
  GameVersionModel toGameVersion(VersionModel versionModel);
}
