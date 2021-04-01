package com.renke.resauce.service;

import com.renke.resauce.model.base.GameVersionModel;
import com.renke.resauce.model.base.VersionTypeModel;
import java.util.List;

public interface GameVersionService {
  List<GameVersionModel> listGameVersions(List<VersionTypeModel> types);
}
