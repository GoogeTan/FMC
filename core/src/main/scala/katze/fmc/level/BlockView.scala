package katze.fmc.level

import katze.fmc.*
import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block.state.{ BlockState, Properties }

trait BlockView[+F[_], -Level]:
  def blockPropertiesAt(level : Level, pos : BlockPos) : F[Properties]
  def blockAt(level : Level, pos: BlockPos) : F[BlockRegistryEntry]
  def blockStateAt(level : Level, pos: BlockPos): F[BlockState]
end BlockView


def blockPropertiesAt[F[_], Level](level: Level, pos: BlockPos)(using gb : BlockView[F, Level]): F[Properties] =
  gb.blockPropertiesAt(level, pos)
end blockPropertiesAt

def blockAt[F[_], Level](level: Level, pos: BlockPos)(using gb : BlockView[F, Level]): F[BlockRegistryEntry] =
  gb.blockAt(level, pos)
end blockAt

def blockStateAt[F[_], Level](level: Level, pos: BlockPos)(using gb : BlockView[F, Level]): F[BlockState] =
  gb.blockStateAt(level, pos)
end blockStateAt
