package katze.fmc.item

import katze.fmc.item.stack.Stack

// TODO протипизировать всё это чудо
final case class ArmorMaterial(
                                durabilityForSlot : ArmorSlot => Int,
                                defenseForSlot : ArmorSlot => Int,
                                enchantmentValue: Int,
                                // equipSound: Any,  TODO сделать звуки в модах.
                                repairIngredient: Stack, // TODO заменить на полноценный инградиент
                                name: String,
                                toughness: Float,
                                knockbackResistance: Float,
                              )