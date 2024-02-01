package katze.fmc.forge.syntax

import katze.fmc.forge.syntax.blockstate.asProperties
import net.minecraftforge.registries.ForgeRegistries
import katze.fmc.potion as fpotion
import net.minecraft.world.effect as vpotion

object potion {
  given asVanila: Conversion[fpotion.PotionEffect, vpotion.MobEffect] = potion => ForgeRegistries.MOB_EFFECTS.getValue(potion.location)
  given asFmc: Conversion[vpotion.MobEffect, fpotion.PotionEffect] = potion=>{
    val location = ForgeRegistries.MOB_EFFECTS.getKey(potion)
    fpotion.PotionEffect(location)
  }

}
