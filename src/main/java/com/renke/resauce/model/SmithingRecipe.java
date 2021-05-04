package com.renke.resauce.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class SmithingRecipe implements Recipe {
  RecipeType type;
  TagItemReference base;
  TagItemReference addition;
  TagItemReference result;
}
