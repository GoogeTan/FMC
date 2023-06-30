package me.zahara.fmc
package syntax

import me.zahara.fmc.block.Properties
import me.zahara.fmc.level.GetBlock

object level:
  def blockPropertiesAt[F[_], Level](level : Level, pos : BlockPos)(using gb : GetBlock[F, Level]) : F[Properties] = gb.blockPropertiesAt(level, pos)
end level
