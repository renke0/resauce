package com.renke.resauce.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class LootingEnchantFunction implements PoolFunction {
  PoolFunctionName function;
  DecimalRange count;
  Integer limit;
}
