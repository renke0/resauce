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
public class FurnaceSmeltFunction implements PoolFunction {
  PoolFunctionName function;
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @Singular
  List<Condition> conditions;
}
