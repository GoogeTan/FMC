package me.zahara.fmc
package item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.Positive

enum ItemType:
  case Common(
               maxStackSize : Int :| Positive
             )
  case Food(
              maxStackSize : Int :| Positive,
              nutrition: Int = 0,
              saturationModifier: Float = .0,
              isMeat: Boolean = false,
              canAlwaysEat: Boolean = false,
              fastFood: Boolean = false,
              effects : List[(Nothing, Float)] = List() // Effect and chance. TODO
           )
  case Tool(
             toolType: ToolType,
             maxDamage : Int
           )
  case Armor(
              slot: ArmorSlot,
              material: ArmorMaterial,
              defense: Int = 0,
              toughness: Float = 0,
              knockbackResistance: Float = 0,
              maxDamage : Int
            )
end ItemType
