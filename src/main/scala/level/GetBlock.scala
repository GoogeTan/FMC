package me.zahara.fmc
package level

import block.{Block, Properties}

trait GetBlock[F[_], Level]:
  extension (level : Level)
    def blockPropertiesAt(pos : BlockPos) : F[Properties]
    def blockAt(pos: BlockPos) : F[Block]
  end extension
end GetBlock
