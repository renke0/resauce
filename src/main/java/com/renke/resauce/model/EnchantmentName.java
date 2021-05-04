package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnchantmentName {
  FORTUNE("minecraft:fortune"),
  SILK_TOUCH("minecraft:silk_touch"),
  SOUL_SPEED("minecraft:soul_speed");

  @JsonValue
  private final String name;
}
