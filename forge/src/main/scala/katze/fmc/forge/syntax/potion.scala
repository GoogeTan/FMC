package katze.fmc.forge.syntax

import katze.fmc.potion as fpotion
import net.minecraft.world.effect as vpotion
import net.minecraftforge.registries.ForgeRegistries

object potion {
  given asVanila: Conversion[fpotion.PotionEffect, vpotion.MobEffect] = potion => ForgeRegistries.MOB_EFFECTS.getValue(potion.location)

  given asFmc: Conversion[vpotion.MobEffect, fpotion.PotionEffect] = potion => fpotion.PotionEffect(ForgeRegistries.MOB_EFFECTS.getKey(potion))
}
