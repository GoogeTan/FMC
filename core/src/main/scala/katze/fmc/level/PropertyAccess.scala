package katze.fmc.level

import katze.fmc.BlockPos
import katze.fmc.block.state.{ BlockState, Property }
import katze.fmc.data.*
import katze.fmc.syntax.all.{*, given}
import katze.fmc.block.state.*

/**
 * Обновляет проперти на данных координатах. Если проперти есть у данного блока, возвращает старое значение, иначе None
 */
def updatePropertyAt[F[_] : Monad, Level, T](level: Level, pos: BlockPos, property: Property[T], newValue: T)(using P : BlockAccess[F, Level]): F[Option[T]] =
  for
    oldState <- P.blockStateAt(level, pos)
    _ <- withValue(oldState, property, newValue).traverse(P.updateBlockAt_(level, pos, _))
  yield valueFromState(oldState, property)
end updatePropertyAt
