package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class Flags {
  @JsonAlias("is_on_fire")
  Boolean onFire;
}
