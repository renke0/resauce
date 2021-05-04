package com.renke.resauce.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.renke.resauce.model.deserialization.RollsDeserializer;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
@JsonDeserialize(using = RollsDeserializer.class)
public class Rolls {
  BigDecimal min;
  BigDecimal max;
  Integer absolute;
  RollsType type;
}
