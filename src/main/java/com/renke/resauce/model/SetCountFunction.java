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
public class SetCountFunction implements PoolFunction {
  PoolFunctionName function;
  Rolls count;
  @Singular
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  List<Condition> conditions;
}
