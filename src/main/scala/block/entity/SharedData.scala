package fmc
package block.entity

import data.*
import level.Level
import syntax.monad.*

final case class SharedData[F[_], T](
                                       fromWorld : (Level, BlockPos) => F[Option[T]],
                                       writeToWorld : (Level, BlockPos, T) => F[Boolean]
                                     )

/**
 * Позволяет произвести вычисления над данными блока в мире.
 */
def process[F[_] : Monad, T, U](level: Level, pos : BlockPos, shared : SharedData[F, T])(process : T => F[(T, U)]) : F[Option[U]] =
  shared.fromWorld(level, pos) >>= {
    case Some(sharedValue) =>
      process(sharedValue) >>= { (newSharedValue, result) =>
        shared.writeToWorld(level, pos, newSharedValue) *> pure(Some(result)) // Дропаем результат, так как при удачном чтении запись так же должна быть удачной
      }
    case None => pure(None)
  }
end process
