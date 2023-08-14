package fmc
package level

import block.state.Property

trait PropertiesAtPos[F[_]]:
  /**
   * Обновляет проперти на данных координатах. Если проперти есть у данного блока, возвращает его старое значение, иначе None
   */
  def updatePropertyAt[T <: Comparable[T]](level : Level, pos: BlockPos, property: Property[T], newValue: T): F[Option[T]]
end PropertiesAtPos


/**
 * Обновляет проперти на данных координатах. Если проперти есть у данного блока, возвращает его старое значение, иначе None
 */
def updatePropertyAt[F[_], T <: Comparable[T]](level: Level, pos: BlockPos, property: Property[T], newValue: T)(using ppos : PropertiesAtPos[F]): F[Option[T]] =
  ppos.updatePropertyAt(level, pos, property, newValue)
end updatePropertyAt
