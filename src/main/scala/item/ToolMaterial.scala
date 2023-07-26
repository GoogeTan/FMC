package me.zahara.fmc
package item

final case class ToolMaterial(
  durability: Int,
  miningSpeedMultiplier: Float,
  attackDamage: Float,
  miningLevel: Int,
  enchantability: Int,
) // TODO инградиент починки