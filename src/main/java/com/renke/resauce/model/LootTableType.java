package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LootTableType {
  BARTER("minecraft:barter"),
  BLOCK("minecraft:block"),
  CHEST("minecraft:chest"),
  ENTITY("minecraft:entity"),
  FISHING("minecraft:fishing"),
  GIFT("minecraft:gift");

  @JsonValue
  private final String value;
}
