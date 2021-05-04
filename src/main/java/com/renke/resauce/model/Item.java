package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.util.Set;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class Item {
  String id;
  String name;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  Set<Recipe> recipes;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  Set<LootTable> lootTables;
}
