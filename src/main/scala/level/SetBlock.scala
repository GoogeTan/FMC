package fmc
package level

import block.state.BlockState

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 *
 * @tparam F Эффект
 */
trait SetBlock[F[_]]:
  def updateBlockAt(level : Level, position: BlockPos, withNewState: BlockState): F[Unit]
end SetBlock

def updateBlockAt[F[_]](level: Level, position: BlockPos, withNewState: BlockState)(using sb : SetBlock[F]): F[Unit] =
  sb.updateBlockAt(level, position, withNewState)
end updateBlockAt
