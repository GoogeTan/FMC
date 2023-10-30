package katze.fmc.level

import katze.fmc.BlockPos
import katze.fmc.block.state.BlockState

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 *
 * @tparam F Эффект
 */
trait BlockAccess[F[_], Level] extends BlockView[F, Level]:
  def updateBlockAt(level : Level, position: BlockPos, withNewState: BlockState): F[Unit]
end BlockAccess

def updateBlockAt[F[_], Level](level: Level, position: BlockPos, withNewState: BlockState)(using sb : BlockAccess[F, Level]): F[Unit] =
  sb.updateBlockAt(level, position, withNewState)
end updateBlockAt
