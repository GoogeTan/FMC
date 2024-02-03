package katze.fmc.forge.registry

import katze.fmc.ResourceLocation
import katze.fmc.forge.syntax.potion
import katze.fmc.item.ItemType.*
import katze.fmc.item.{ItemPrototype, ItemType}
import katze.fmc.potion.{PotionEffect, PotionEffectPattern}
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item

final class DefaultItemImplementation extends ItemImplementation :
  override def implementItem(prototype: ItemPrototype): Item =
    new Item(prototype.itemType match {
      case t: Common => commonProperties(t)
      case t: Food => foodProperties(t)
      case t: Tool => toolProperties(t)
      case t: Armor => armorProperties(t)
    })
  end implementItem

  def commonProperties(itemType: ItemType.Common): Item.Properties = new Item.Properties().stacksTo(itemType.maxStackSize)

  def foodProperties(itemType: ItemType.Food): Item.Properties =
    val foodProperties = new FoodProperties.Builder()
      .nutrition(itemType.nutrition)
      .saturationMod(itemType.saturationModifier)

    if (itemType.isMeat) {
      foodProperties.meat()
    }
    if (itemType.canAlwaysEat) {
      foodProperties.alwaysEat()
    }
    if (itemType.fastFood) {
      foodProperties.fast()
    }

    for (PotionEffectPattern(effect, chance, duration, amplifier, isAmbient, isVisible, shouldShowIconInInventory) <- itemType.effects) {
      foodProperties.effect(() => new MobEffectInstance(effect, duration, amplifier, isAmbient, isVisible, shouldShowIconInInventory), chance)
    }

    new Item.Properties().stacksTo(itemType.maxStackSize).food(foodProperties.build())
  end foodProperties


  def toolProperties(itemType: ItemType.Tool): Item.Properties = new Item.Properties().stacksTo(1).durability(itemType.material.durability)

  def armorProperties(itemType: ItemType.Armor): Item.Properties = new Item.Properties().stacksTo(1).durability(itemType.material.durabilityForSlot(slot))

end DefaultItemImplementation
