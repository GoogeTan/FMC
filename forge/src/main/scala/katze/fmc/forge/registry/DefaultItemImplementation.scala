package katze.fmc.forge.registry

import katze.fmc.ResourceLocation
import katze.fmc.item.ItemPrototype
import katze.fmc.item.ItemType.*
import katze.fmc.potion.{PotionEffect, PotionEffectPattern}
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import katze.fmc.forge.syntax.potion.given

class DefaultItemImplementation extends ItemImplementation {
  override def implementItem(prototype: ItemPrototype): Item = {
    val properties = new Item.Properties()
    prototype.itemType match {
      case Common(maxStackSize) =>
        properties.stacksTo(maxStackSize)

      case Food(maxStackSize, nutrition, saturationModifier, isMeat, canAlwaysEat, fastFood, effects) =>
        properties.stacksTo(maxStackSize)

        val foodProperties = new FoodProperties.Builder()
          .nutrition(nutrition)
          .saturationMod(saturationModifier)

        if (isMeat) {
          foodProperties.meat()
        }
        if (canAlwaysEat) {
          foodProperties.alwaysEat()
        }
        if (fastFood) {
          foodProperties.fast()
        }

        for(PotionEffectPattern(effect, chance, duration, amplifier, isAmbient, isVisible, shouldShowIconInInventory) <- effects) {
          foodProperties.effect(() => new MobEffectInstance(effect, duration, amplifier, isAmbient, isVisible, shouldShowIconInInventory), chance)
        }

        properties.food(foodProperties.build())

      case Tool(toolType, attackSpeed, material) =>
        properties.stacksTo(1)
        properties.durability(material.durability)

      case Armor(slot, material) =>
        properties.stacksTo(1)
        properties.durability(material.durabilityForSlot(slot))
    }
    new Item(properties)
  }
}
