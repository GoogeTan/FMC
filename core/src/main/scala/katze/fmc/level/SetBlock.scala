package katze.fmc.level

import katze.fmc.BlockPos
import katze.fmc.block.state.BlockState

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 *
 * @tparam F Эффект
 */
trait SetBlock[F[_], Level]:
  def updateBlockAt(level : Level, position: BlockPos, withNewState: BlockState): F[Unit]
end SetBlock

def updateBlockAt[F[_], Level](level: Level, position: BlockPos, withNewState: BlockState)(using sb : SetBlock[F, Level]): F[Unit] =
  sb.updateBlockAt(level, position, withNewState)
end updateBlockAt
