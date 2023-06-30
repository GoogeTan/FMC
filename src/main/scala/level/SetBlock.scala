package me.zahara.fmc
package level

import block.{Properties, Property}

/**
 * Для данного типа уровня Level позволяет обновлять проперти блоков в мире, устанавливать блоки в мир.
 * @tparam F Эффект
 * @tparam Level Мир
 */
trait SetBlock[F[_], Level]:
  def updateBlockAt(level: Level, pos: BlockPos, resourceLocation: ResourceLocation): F[Unit]

  def updateBlockAt(level: Level, pos: BlockPos, resourceLocation: ResourceLocation, properties: Properties): F[Unit]
end SetBlock

