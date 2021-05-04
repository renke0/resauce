package com.renke.resauce.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@With
@Jacksonized
public class Enchantment {
  EnchantmentName enchantment;
  EnchantmentLevel levels;
}
