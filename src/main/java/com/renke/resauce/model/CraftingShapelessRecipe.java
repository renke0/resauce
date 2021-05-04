package com.renke.resauce.model;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class CraftingShapelessRecipe implements Recipe {
  RecipeType type;
  String group;
  List<List<TagItemReference>> ingredients;
  CraftingResult result;
}

