package fmc
package level

import block.Block
import block.state.{BlockState, Properties}

trait GetBlock[F[_]]:
  def blockPropertiesAt(level : Level, pos : BlockPos) : F[Properties]
  def blockAt(level : Level, pos: BlockPos) : F[Block]
  def blockStateAt(level : Level, pos: BlockPos): F[BlockState]
end GetBlock


def blockPropertiesAt[F[_]](level: Level, pos: BlockPos)(using gb : GetBlock[F]): F[Properties] =
  gb.blockPropertiesAt(level, pos)
end blockPropertiesAt

def blockAt[F[_]](level: Level, pos: BlockPos)(using gb : GetBlock[F]): F[Block] =
  gb.blockAt(level, pos)
end blockAt

def blockStateAt[F[_]](level: Level, pos: BlockPos)(using gb : GetBlock[F]): F[BlockState] =
  gb.blockStateAt(level, pos)
end blockStateAt
