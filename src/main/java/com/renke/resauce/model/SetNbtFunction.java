package com.renke.resauce.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class SetNbtFunction implements PoolFunction {
  PoolFunctionName function;
  String tag;
}
