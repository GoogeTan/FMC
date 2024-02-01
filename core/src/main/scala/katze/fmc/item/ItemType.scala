package katze.fmc.item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.Positive
import katze.fmc.potion.PotionEffectPattern

enum ItemType:
  case Common(
               maxStackSize : Int :| Positive
             )
  case Food(
             maxStackSize      : Int :| Positive,
             nutrition         : Int = 0,
             saturationModifier: Float = .0,
             isMeat            : Boolean = false,
             canAlwaysEat      : Boolean = false,
             fastFood          : Boolean = false,
             effects           : List[PotionEffectPattern] = List() // TODO Добавить эффект и его шанс
           )
  case Tool(
             toolType     : ToolType,
             attackSpeed  : Float :| Positive,
             material     : ToolMaterial
           )
  case Armor(
              slot: ArmorSlot,
              material: ArmorMaterial
            )
end ItemType
