package me.zahara.fmc
package item

case class ArmorMaterial(
                          durabilityForSlot : ArmorSlot => Int,
                          defenseForSlot : ArmorSlot => Int,
                          enchantmentValue: Int,
                          equipSound: Any, // TODO
                          repairIngredient: Any, // TODO
                          name: String,
                          toughness: Float,
                          knockbackResistance: Float,
                        )
