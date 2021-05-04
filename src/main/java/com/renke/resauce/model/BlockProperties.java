package com.renke.resauce.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class BlockProperties {
  String type;
  String half;
  String part;
  Integer age;
  Integer level;
  Boolean unstable;
  Integer pickles;
  Integer layers;
}
