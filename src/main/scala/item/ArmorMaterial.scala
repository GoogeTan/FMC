package me.zahara.fmc
package item

import item.stack.Stack

// TODO протипизировать всё это чудо
case class ArmorMaterial(
                          durabilityForSlot : ArmorSlot => Int,
                          defenseForSlot : ArmorSlot => Int,
                          enchantmentValue: Int,
                          // equipSound: Any,  TODO сделать звуки в модах.
                          repairIngredient: Stack, // TODO заменить на полноценный инградиент
                          name: String,
                          toughness: Float,
                          knockbackResistance: Float,
                        )
