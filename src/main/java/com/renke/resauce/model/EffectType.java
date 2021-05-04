package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EffectType {
  BLINDNESS("minecraft:blindness"),
  JUMP_BOOST("minecraft:jump_boost"),
  NIGHT_VISION("minecraft:night_vision"),
  POISON("minecraft:poison"),
  SATURATION("minecraft:saturation"),
  WEAKNESS("minecraft:weakness");

  @JsonValue
  private final String value;
}
