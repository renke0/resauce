package com.renke.resauce.repository;

import java.util.Set;

public interface ResourceRepository {
  Set<String> resolveVersions();

  String itemList(String versionId);

  Set<String> loadRecipes(String versionId);

  Set<String> loadLootTables(String versionId);
}
