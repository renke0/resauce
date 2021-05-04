package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.math.BigDecimal;
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
public class Condition {
  @JsonAlias("condition")
  ConditionType name;
  PoolPredicate predicate;
  EnchantmentName enchantment;
  String block;
  String entity;
  BlockProperties properties;
  Integer offsetY;
  @JsonAlias("looting_multiplier")
  BigDecimal lootingMultiplier;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<BigDecimal> chances;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Condition> terms;
}
