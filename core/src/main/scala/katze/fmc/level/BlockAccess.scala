package katze.fmc.level

import katze.fmc.BlockPos
import katze.fmc.block.state.BlockState
import katze.fmc.data.*
import katze.fmc.syntax.all.{*, given}

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 *
 * @tparam F Эффект
 */
trait BlockAccess[F[_], Level] extends BlockView[F, Level]:
  def updateBlockAt(level : Level, position: BlockPos, withNewState: BlockState): F[BlockState]
  def updateBlockAt_(level : Level, position: BlockPos, withNewState: BlockState): F[Unit]
end BlockAccess

def updateBlockAt[F[_], Level](level: Level, position: BlockPos, withNewState: BlockState)(using sb : BlockAccess[F, Level]): F[BlockState] =
  sb.updateBlockAt(level, position, withNewState)
end updateBlockAt

def updateBlockAt_[F[_], Level](level: Level, position: BlockPos, withNewState: BlockState)(using sb : BlockAccess[F, Level]): F[Unit] =
  sb.updateBlockAt_(level, position, withNewState)
end updateBlockAt_


def updateBlockAt[F[_] : Monad, Level](level: Level, pos: BlockPos, f: BlockState => Option[BlockState])(using B : BlockAccess[F, Level]): F[Option[BlockState]] =
  for
    currentState <- B.blockStateAt(level, pos)
    result <-  f(currentState).traverse(B.updateBlockAt(level, pos, _))
  yield result
end updateBlockAt
