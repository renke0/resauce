package com.renke.resauce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renke.resauce.ResauceTest;
import com.renke.resauce.common.parsing.JsonParser;
import com.renke.resauce.model.Condition;
import com.renke.resauce.model.CraftingResult;
import com.renke.resauce.model.CraftingShapedRecipe;
import com.renke.resauce.model.Item;
import com.renke.resauce.model.LootTable;
import com.renke.resauce.model.Pool;
import com.renke.resauce.repository.ResourceRepository;
import java.util.List;
import java.util.Set;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DefaultGameMetadataServiceTest extends ResauceTest {
  @Mock
  ResourceRepository resourceRepository;
  @Spy
  ObjectMapper objectMapper = new ObjectMapper();
  @Spy
  JsonParser jsonParser = new JsonParser(objectMapper);
  @InjectMocks
  DefaultGameMetadataService service;

  @Override
  protected void extraEasyRandomConfiguration(EasyRandomParameters parameters) {
    parameters.excludeField(inClass(Item.class).and(named("lootTables")))
        .excludeField(inClass(Item.class).and(named("recipes")))
        .excludeField(inClass(Pool.class).and(named("functions")))
        .excludeField(inClass(Pool.class).and(named("children")))
        .excludeField(inClass(Pool.class).and(named("entries")))
        .excludeField(inClass(LootTable.class).and(named("functions")))
        .excludeField(inClass(Condition.class).and(named("terms")));
  }

  @Nested
  @DisplayName("loadGameMetadata")
  class LoadGameMetadataTest {
    @Test
    @DisplayName("Should load all the metadata associated with the game at once")
    void testLoadGameData() {
      var items = nextSet(Item.class);
      var recipes = nextSet(CraftingShapedRecipe.class);
      var lootTables = nextSet(LootTable.class);

      when(resourceRepository.resolveVersions()).thenReturn(Set.of("1.16", "1.15", "1.14"));
      when(resourceRepository.itemList(anyString())).thenReturn(json(items));
      when(resourceRepository.loadRecipes(anyString()))
          .thenReturn(map(recipes, DefaultGameMetadataServiceTest.this::json));
      when(resourceRepository.loadLootTables(anyString()))
          .thenReturn(map(lootTables, DefaultGameMetadataServiceTest.this::json));

      var actual = service.loadGameMetadata();

      assertEquals(3, actual.getVersions().size());
      actual.getVersions().forEach(v -> assertFalse(v.getItems().isEmpty()));
    }

    @Test
    @DisplayName("Should correctly assign the recipes to the respective items")
    void testLoadGameDataAssignRecipes() {
      var item = nextObject(Item.class).withId("minecraft:cobbelstone");
      var recipes = List.of(
          nextObject(CraftingShapedRecipe.class)
              .withResult(nextObject(CraftingResult.class).withItem("minecraft:cobblestone"))
      );
    }
  }
}
