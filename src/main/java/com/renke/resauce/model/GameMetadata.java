package com.renke.resauce.model;

import java.util.Set;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class GameMetadata {
  Set<Version> versions;
}
