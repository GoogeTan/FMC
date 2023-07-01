package me.zahara.fmc
package level

import block.{Properties, Property}

trait PropertiesAtPos[F[_], Level]:
  extension (level : Level)
    /**
     * Обновляет проперти на данных координатах. Если проперти есть у данного блока, возвращает его старое значение, иначе None
     */
    def updatePropertyAt[T](pos: BlockPos, property: Property[T], newValue: T): F[Option[T]]
  end extension
end PropertiesAtPos
