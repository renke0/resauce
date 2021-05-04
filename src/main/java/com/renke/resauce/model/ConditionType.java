package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConditionType {
  ALTERNATIVE("minecraft:alternative"),
  BLOCK_STATE_PROPERTY("minecraft:block_state_property"),
  DAMAGE_SOURCE_PROPERTIES("minecraft:damage_source_properties"),
  ENTITY_PROPERTIES("minecraft:entity_properties"),
  INVERTED("minecraft:inverted"),
  KILLED_BY_PLAYER("minecraft:killed_by_player"),
  LOCATION_CHECK("minecraft:location_check"),
  MATCH_TOOL("minecraft:match_tool"),
  RANDOM_CHANCE("minecraft:random_chance"),
  RANDOM_CHANCE_WITH_LOOTING("minecraft:random_chance_with_looting"),
  SURVIVES_EXPLOSION("minecraft:survives_explosion"),
  TABLE_BONUS("minecraft:table_bonus");

  @JsonValue
  private final String value;
}
