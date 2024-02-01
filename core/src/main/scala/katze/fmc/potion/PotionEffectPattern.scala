package katze.fmc.potion

import katze.fmc.ResourceLocation

case class PotionEffectPattern(effect: PotionEffect,
                               chance: Double,
                               duration: Int,
                               amplifier: Int,
                               isAmbient: Boolean,
                               isVisible: Boolean,
                               shouldShowIconInInventory: Boolean)
