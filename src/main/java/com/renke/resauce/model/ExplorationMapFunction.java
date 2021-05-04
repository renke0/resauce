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
public class ExplorationMapFunction implements PoolFunction {
  PoolFunctionName function;
  String destination;
  String decoration;
  Integer zoom;
  @JsonAlias("skip_existing_chunks")
  Boolean skipExistingChunks;
}
