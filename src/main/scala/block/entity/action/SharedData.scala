package me.zahara.fmc
package block.entity.action

import level.Level
import syntax.cats.{*, given}

import cats.data.OptionT
import cats.{Functor, Monad, Semigroup}

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
        shared.writeToWorld(level, pos, newSharedValue) *> Some(result).pure // Дропаем результат, так как при удачном чтенгии запись так же должна быть удачной
      }
    case None => None.pure[F]
  }
end process
