package com.renke.resauce.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class DecimalRange {
  BigDecimal min;
  BigDecimal max;
}
