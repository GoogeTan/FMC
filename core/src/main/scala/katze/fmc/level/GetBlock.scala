package katze.fmc.level

import katze.fmc.*
import katze.fmc.block.Block
import katze.fmc.block.state.{ BlockState, Properties }

trait GetBlock[F[_], Level]:
  def blockPropertiesAt(level : Level, pos : BlockPos) : F[Properties]
  def blockAt(level : Level, pos: BlockPos) : F[Block]
  def blockStateAt(level : Level, pos: BlockPos): F[BlockState]
end GetBlock


def blockPropertiesAt[F[_], Level](level: Level, pos: BlockPos)(using gb : GetBlock[F, Level]): F[Properties] =
  gb.blockPropertiesAt(level, pos)
end blockPropertiesAt

def blockAt[F[_], Level](level: Level, pos: BlockPos)(using gb : GetBlock[F, Level]): F[Block] =
  gb.blockAt(level, pos)
end blockAt

def blockStateAt[F[_], Level](level: Level, pos: BlockPos)(using gb : GetBlock[F, Level]): F[BlockState] =
  gb.blockStateAt(level, pos)
end blockStateAt
