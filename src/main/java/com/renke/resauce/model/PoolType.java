package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PoolType {
  ALTERNATIVES("minecraft:alternatives"),
  EMPTY("minecraft:empty"),
  ITEM("minecraft:item"),
  LOOT_TABLE("minecraft:loot_table"),
  TAG("minecraft:tag");

  @JsonValue
  private final String value;
}
