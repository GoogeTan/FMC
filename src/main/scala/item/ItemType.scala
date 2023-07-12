package me.zahara.fmc
package item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.Positive

// Добавить типизацию точную
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
              effects : List[(Nothing, Float)] = List() // TODO Добавить эффект и его шанс
           )
  case Tool(
             toolType: ToolType,
             maxDamage : Int :| Positive,
             material: ToolMaterial
           )
  case Armor(
              slot: ArmorSlot,
              material: ArmorMaterial,
              defense: Int = 0,
              toughness: Float = 0,
              knockbackResistance: Float = 0,
              maxDamage : Int :| Positive
            )
end ItemType
