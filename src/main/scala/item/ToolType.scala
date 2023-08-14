package fmc
package item

import syntax.all.{*, given}

enum ToolType:
  case None
  case Axe
  case Sword
  case Pickaxe
  case Shovel
  case Hoe
  case MultiTool(types : List[ToolType] :| MinLength[1])
end ToolType
