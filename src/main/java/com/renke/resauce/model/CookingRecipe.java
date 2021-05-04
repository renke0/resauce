package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class CookingRecipe implements Recipe {
  RecipeType type;
  List<TagItemReference> ingredient;
  String result;
  BigDecimal experience;
  @JsonAlias("cookingtime")
  Integer cookingTime;
}
