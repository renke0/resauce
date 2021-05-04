package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class PoolPredicate {
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Enchantment> enchantments;
  String item;
  String biome;
  String type;
  Flags flags;
  Block block;
  @JsonAlias("fishing_hook")
  FishingHook fishingHook;
  @JsonAlias("is_lightning")
  Boolean lightning;
}
