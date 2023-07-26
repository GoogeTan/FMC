package me.zahara.fmc
package level

import block.{Properties, Property}

trait PropertiesAtPos[F[_]]:
  extension (level : Level)
    /**
     * Обновляет проперти на данных координатах. Если проперти есть у данного блока, возвращает его старое значение, иначе None
     */
    def updatePropertyAt[T <: Comparable[T]](pos: BlockPos, property: Property[T], newValue: T): F[Option[T]]
  end extension
end PropertiesAtPos
