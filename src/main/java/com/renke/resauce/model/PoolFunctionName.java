package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@RequiredArgsConstructor
@Getter
public enum PoolFunctionName {
  APPLY_BONUS(Names.APPLY_BONUS),
  COPY_NAME(Names.COPY_NAME),
  COPY_NBT(Names.COPY_NBT),
  COPY_STATE(Names.COPY_STATE),
  ENCHANT_RANDOMLY(Names.ENCHANT_RANDOMLY),
  ENCHANT_WITH_LEVELS(Names.ENCHANT_WITH_LEVELS),
  EXPLORATION_MAP(Names.EXPLORATION_MAP),
  EXPLOSION_DECAY(Names.EXPLOSION_DECAY),
  FURNACE_SMELT(Names.FURNACE_SMELT),
  LIMIT_COUNT(Names.LIMIT_COUNT),
  LOOTING_ENCHANT(Names.LOOTING_ENCHANT),
  SET_CONTENTS(Names.SET_CONTENTS),
  SET_COUNT(Names.SET_COUNT),
  SET_DAMAGE(Names.SET_DAMAGE),
  SET_DATA(Names.SET_DATA),
  SET_NBT(Names.SET_NBT),
  SET_STEW_EFFECT(Names.SET_STEW_EFFECT);

  @JsonValue
  private final String value;

  @UtilityClass
  public static class Names {
    public static final String APPLY_BONUS = "minecraft:apply_bonus";
    public static final String COPY_NAME = "minecraft:copy_name";
    public static final String COPY_NBT = "minecraft:copy_nbt";
    public static final String COPY_STATE = "minecraft:copy_state";
    public static final String ENCHANT_RANDOMLY = "minecraft:enchant_randomly";
    public static final String ENCHANT_WITH_LEVELS = "minecraft:enchant_with_levels";
    public static final String EXPLORATION_MAP = "minecraft:exploration_map";
    public static final String EXPLOSION_DECAY = "minecraft:explosion_decay";
    public static final String FURNACE_SMELT = "minecraft:furnace_smelt";
    public static final String LIMIT_COUNT = "minecraft:limit_count";
    public static final String LOOTING_ENCHANT = "minecraft:looting_enchant";
    public static final String SET_CONTENTS = "minecraft:set_contents";
    public static final String SET_COUNT = "minecraft:set_count";
    public static final String SET_DAMAGE = "minecraft:set_damage";
    public static final String SET_DATA = "minecraft:set_data";
    public static final String SET_NBT = "minecraft:set_nbt";
    public static final String SET_STEW_EFFECT = "minecraft:set_stew_effect";
  }
}
