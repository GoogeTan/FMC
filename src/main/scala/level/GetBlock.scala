package me.zahara.fmc
package level

import block.{Block, BlockState, Properties}

trait GetBlock[F[_], Level]:
  extension (level : Level)
    def blockPropertiesAt(pos : BlockPos) : F[Properties]
    def blockAt(pos: BlockPos) : F[Block]
    def blockStateAt(pos: BlockPos): F[BlockState]
  end extension
end GetBlock
