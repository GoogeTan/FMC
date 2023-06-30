package me.zahara.fmc
package level

import block.Properties

trait GetBlock[F[_], Level]:
  def blockPropertiesAt(level : Level, pos : BlockPos) : F[Properties]
  def blockAt(level: Level, pos: BlockPos) : F[ResourceLocation]
end GetBlock
