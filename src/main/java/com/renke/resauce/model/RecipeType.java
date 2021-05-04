package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
public enum RecipeType {
  CRAFTING_SHAPED(Names.CRAFTING_SHAPED),
  CRAFTING_SHAPELESS(Names.CRAFTING_SHAPELESS),
  SMELTING(Names.SMELTING),
  CAMPFIRE_COOKING(Names.CAMPFIRE_COOKING),
  SMOKING(Names.SMOKING),
  BLASTING(Names.BLASTING),
  SMITHING(Names.SMITHING),
  STONECUTTING(Names.STONECUTTING),
  CRAFTING_SPECIAL_ARMOR_DYE(Names.CRAFTING_SPECIAL_ARMOR_DYE),
  CRAFTING_SPECIAL_BANNER_DUPLICATE(Names.CRAFTING_SPECIAL_BANNER_DUPLICATE),
  CRAFTING_SPECIAL_BANNER_ADD_PATTERN(Names.CRAFTING_SPECIAL_BANNER_ADD_PATTERN),
  CRAFTING_SPECIAL_BOOK_CLONING(Names.CRAFTING_SPECIAL_BOOK_CLONING),
  CRAFTING_SPECIAL_FIREWORK_ROCKET(Names.CRAFTING_SPECIAL_FIREWORK_ROCKET),
  CRAFTING_SPECIAL_FIREWORK_STAR(Names.CRAFTING_SPECIAL_FIREWORK_STAR),
  CRAFTING_SPECIAL_FIREWORK_STAR_FADE(Names.CRAFTING_SPECIAL_FIREWORK_STAR_FADE),
  CRAFTING_SPECIAL_MAP_CLONING(Names.CRAFTING_SPECIAL_MAP_CLONING),
  CRAFTING_SPECIAL_MAP_EXTENDING(Names.CRAFTING_SPECIAL_MAP_EXTENDING),
  CRAFTING_SPECIAL_REPAIR_ITEM(Names.CRAFTING_SPECIAL_REPAIR_ITEM),
  CRAFTING_SPECIAL_SHIELD_DECORATION(Names.CRAFTING_SPECIAL_SHIELD_DECORATION),
  CRAFTING_SPECIAL_SHULKERBOX_COLORING(Names.CRAFTING_SPECIAL_SHULKER_BOX_COLORING),
  CRAFTING_SPECIAL_SUSPICIOUS_STEW(Names.CRAFTING_SPECIAL_SUSPICIOUS_STEW),
  CRAFTING_SPECIAL_TIPPED_ARROW(Names.CRAFTING_SPECIAL_TIPPED_ARROW);

  @JsonValue
  private final String name;

  @UtilityClass
  public static class Names {
    public static final String CRAFTING_SHAPED = "minecraft:crafting_shaped";
    public static final String CRAFTING_SHAPELESS = "minecraft:crafting_shapeless";
    public static final String SMELTING = "minecraft:smelting";
    public static final String CAMPFIRE_COOKING = "minecraft:campfire_cooking";
    public static final String SMOKING = "minecraft:smoking";
    public static final String BLASTING = "minecraft:blasting";
    public static final String SMITHING = "minecraft:smithing";
    public static final String STONECUTTING = "minecraft:stonecutting";
    public static final String CRAFTING_SPECIAL_ARMOR_DYE = "minecraft:crafting_special_armordye";
    public static final String CRAFTING_SPECIAL_BANNER_DUPLICATE = "minecraft:crafting_special_bannerduplicate";
    public static final String CRAFTING_SPECIAL_BANNER_ADD_PATTERN = "minecraft:crafting_special_banneraddpattern";
    public static final String CRAFTING_SPECIAL_BOOK_CLONING = "minecraft:crafting_special_bookcloning";
    public static final String CRAFTING_SPECIAL_FIREWORK_ROCKET = "minecraft:crafting_special_firework_rocket";
    public static final String CRAFTING_SPECIAL_FIREWORK_STAR = "minecraft:crafting_special_firework_star";
    public static final String CRAFTING_SPECIAL_FIREWORK_STAR_FADE = "minecraft:crafting_special_firework_star_fade";
    public static final String CRAFTING_SPECIAL_MAP_CLONING = "minecraft:crafting_special_mapcloning";
    public static final String CRAFTING_SPECIAL_MAP_EXTENDING = "minecraft:crafting_special_mapextending";
    public static final String CRAFTING_SPECIAL_REPAIR_ITEM = "minecraft:crafting_special_repairitem";
    public static final String CRAFTING_SPECIAL_SHIELD_DECORATION = "minecraft:crafting_special_shielddecoration";
    public static final String CRAFTING_SPECIAL_SHULKER_BOX_COLORING = "minecraft:crafting_special_shulkerboxcoloring";
    public static final String CRAFTING_SPECIAL_SUSPICIOUS_STEW = "minecraft:crafting_special_suspiciousstew";
    public static final String CRAFTING_SPECIAL_TIPPED_ARROW = "minecraft:crafting_special_tippedarrow";
  }
}
