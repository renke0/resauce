package com.renke.resauce.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.renke.resauce.model.PoolFunctionName.Names;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "function", visible = true)
@JsonSubTypes({
    @Type(value = ApplyBonusFunction.class, name = Names.APPLY_BONUS),
    @Type(value = CopyNameFunction.class, name = Names.COPY_NAME),
    @Type(value = CopyNbtFunction.class, name = Names.COPY_NBT),
    @Type(value = CopyStateFunction.class, name = Names.COPY_STATE),
    @Type(value = EnchantRandomlyFunction.class, name = Names.ENCHANT_RANDOMLY),
    @Type(value = EnchantWithLevelsFunction.class, name = Names.ENCHANT_WITH_LEVELS),
    @Type(value = ExplorationMapFunction.class, name = Names.EXPLORATION_MAP),
    @Type(value = ExplosionDecayFunction.class, name = Names.EXPLOSION_DECAY),
    @Type(value = FurnaceSmeltFunction.class, name = Names.FURNACE_SMELT),
    @Type(value = LimitCountFunction.class, name = Names.LIMIT_COUNT),
    @Type(value = LootingEnchantFunction.class, name = Names.LOOTING_ENCHANT),
    @Type(value = SetContentsFunction.class, name = Names.SET_CONTENTS),
    @Type(value = SetCountFunction.class, name = Names.SET_COUNT),
    @Type(value = SetDamageFunction.class, name = Names.SET_DAMAGE),
    @Type(value = SetDataFunction.class, name = Names.SET_DATA),
    @Type(value = SetNbtFunction.class, name = Names.SET_NBT),
    @Type(value = SetStewEffectFunction.class, name = Names.SET_STEW_EFFECT)
})
public interface PoolFunction {
  PoolFunctionName getFunction();
}
