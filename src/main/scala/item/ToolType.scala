package me.zahara.fmc
package item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.MinLength

enum ToolType:
  case None
  case Axe
  case Sword
  case Pickaxe
  case Shovel
  case How
  case MultiTool(types : List[ToolType] :| MinLength[1])
end ToolType
