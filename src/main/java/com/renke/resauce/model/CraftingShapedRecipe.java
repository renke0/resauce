package com.renke.resauce.model;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class CraftingShapedRecipe implements Recipe {
  RecipeType type;
  String group;
  List<String> pattern;
  Map<String, List<TagItemReference>> key;
  CraftingResult result;
}
