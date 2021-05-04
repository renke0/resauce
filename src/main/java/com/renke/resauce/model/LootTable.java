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
public class LootTable {
  LootTableType type;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Pool> pools;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<PoolFunction> functions;
}
