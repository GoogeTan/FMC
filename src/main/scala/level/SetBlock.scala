package me.zahara.fmc
package level

import block.{BlockState, Properties, Property}

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 * @tparam F Эффект
 * @tparam Level Мир
 */
trait SetBlock[F[_], Level]:
  extension (level : Level)
    def updateBlockAt(position: BlockPos, withNewState: BlockState): F[Unit]
  end extension
end SetBlock

