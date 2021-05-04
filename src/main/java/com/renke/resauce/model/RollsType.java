package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RollsType {
  BINOMIAL("minecraft:binomial"),
  UNIFORM("minecraft:uniform");

  @JsonValue
  private final String value;
}
