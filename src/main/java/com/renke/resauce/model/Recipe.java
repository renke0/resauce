package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.renke.resauce.model.RecipeType.Names;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
    @Type(value = CraftingShapedRecipe.class, name = Names.CRAFTING_SHAPED),
    @Type(value = CraftingShapelessRecipe.class, name = Names.CRAFTING_SHAPELESS),
    @Type(value = CookingRecipe.class, name = Names.SMELTING),
    @Type(value = CookingRecipe.class, name = Names.CAMPFIRE_COOKING),
    @Type(value = CookingRecipe.class, name = Names.SMOKING),
    @Type(value = CookingRecipe.class, name = Names.BLASTING),
    @Type(value = StonecuttingRecipe.class, name = Names.STONECUTTING),
    @Type(value = SmithingRecipe.class, name = Names.SMITHING),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_ARMOR_DYE),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_BANNER_DUPLICATE),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_BOOK_CLONING),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_FIREWORK_ROCKET),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_FIREWORK_STAR),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_FIREWORK_STAR_FADE),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_MAP_CLONING),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_MAP_EXTENDING),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_REPAIR_ITEM),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_SHIELD_DECORATION),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_SHULKER_BOX_COLORING),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_SUSPICIOUS_STEW),
    @Type(value = CraftingSpecialRecipe.class, name = Names.CRAFTING_SPECIAL_TIPPED_ARROW),
})
public interface Recipe {
  RecipeType getType();
}

