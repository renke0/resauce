package com.renke.resauce.model;

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
public class Pool {
  Rolls rolls;
  PoolType type;
  String name;
  Integer weight;
  Boolean expand;
  Integer quality;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<PoolFunction> functions;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Pool> children;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Pool> entries;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Condition> conditions;
}

