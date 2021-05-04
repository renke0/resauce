package com.renke.resauce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.renke.resauce.common.parsing.JsonParser;
import com.renke.resauce.model.CookingRecipe;
import com.renke.resauce.model.CraftingShapedRecipe;
import com.renke.resauce.model.CraftingShapelessRecipe;
import com.renke.resauce.model.GameMetadata;
import com.renke.resauce.model.Item;
import com.renke.resauce.model.LootTable;
import com.renke.resauce.model.Recipe;
import com.renke.resauce.model.SmithingRecipe;
import com.renke.resauce.model.StonecuttingRecipe;
import com.renke.resauce.model.Version;
import com.renke.resauce.repository.ResourceRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.renke.resauce.common.functions.Functions.map;

@Service
@RequiredArgsConstructor
public class DefaultGameMetadataService implements GameMetadataService {
  private final ResourceRepository resourceRepository;
  private final JsonParser jsonParser;

  @Override
  @Cacheable("metadata")
  public GameMetadata loadGameMetadata() {
    var versions = resourceRepository.resolveVersions();
    return GameMetadata.builder()
        .versions(map(versions, this::loadVersion))
        .build();
  }

  private Version loadVersion(String versionId) {

    Set<Item> items = loadItems(versionId);

    return Version.builder()
        .id(versionId)
        .items(items)
        .build();
  }

  private Set<Item> loadItems(String versionId) {
    var itemsJson = resourceRepository.itemList(versionId);
    var recipes = loadRecipes(versionId);
    var lootTables = loadLootTables(versionId);

    return jsonParser.parseJson(itemsJson, new TypeReference<Set<Item>>() {})
        .stream()
        .map(item -> item.withRecipes(findRecipes(item.getId(), recipes)))
        .map(item -> item.withLootTables(findLootTables(item.getId(), lootTables)))
        .collect(Collectors.toSet());
  }

  private Set<LootTable> loadLootTables(String versionId) {
    return resourceRepository.loadLootTables(versionId)
        .stream()
        .map(s -> jsonParser.parseJson(s, LootTable.class))
        .collect(Collectors.toSet());
  }

  private Set<Recipe> loadRecipes(String versionId) {
    return resourceRepository.loadRecipes(versionId)
        .stream()
        .map(s -> jsonParser.parseJson(s, Recipe.class))
        .collect(Collectors.toSet());
  }

  private Set<LootTable> findLootTables(String itemId, Set<LootTable> lootTables) {
    return lootTables.stream()
        .filter(lootTable -> resolveLootTableResult(lootTable).equals(itemId))
        .collect(Collectors.toSet());
  }

  private Set<Recipe> findRecipes(String itemId, Set<Recipe> recipes) {
    return recipes.stream()
        .filter(recipe -> resolveRecipeResult(recipe).equals(itemId))
        .collect(Collectors.toSet());
  }

  private String resolveRecipeResult(Recipe recipe) {
    switch (recipe.getType()) {
      case CRAFTING_SHAPED:
        return ((CraftingShapedRecipe) recipe).getResult().getItem();
      case CRAFTING_SHAPELESS:
        return ((CraftingShapelessRecipe) recipe).getResult().getItem();
      case BLASTING:
      case CAMPFIRE_COOKING:
      case SMELTING:
      case SMOKING:
        return ((CookingRecipe) recipe).getResult();
      case SMITHING:
        return ((SmithingRecipe) recipe).getResult().getItem();
      case STONECUTTING:
        return ((StonecuttingRecipe) recipe).getResult();
      default:
        return "";
    }
  }

  private String resolveLootTableResult(LootTable lootTable) {
    return "";
  }
}
