package katze.fmc.block.entity

import katze.fmc.*
import katze.fmc.data.*

final case class SharedData[F[_], ServerLevel, T](
                                       fromWorld : (ServerLevel, BlockPos) => F[Option[T]],
                                       writeToWorld : (ServerLevel, BlockPos, T) => F[Boolean]
                                     )

/**
 * Позволяет произвести вычисления над данными блока в мире.
 */
def process[F[_] : Monad, ServerLevel, T, U](level: ServerLevel, pos : BlockPos, shared : SharedData[F, ServerLevel, T])(process : T => F[(T, U)]) : F[Option[U]] =
  shared.fromWorld(level, pos) >>= {
    case Some(sharedValue) =>
      process(sharedValue) >>= { (newSharedValue, result) =>
        shared.writeToWorld(level, pos, newSharedValue) *> Some(result) // Дропаем результат, так как при удачном чтении запись так же должна быть удачной
      }
    case None => pure(None)
  }
end process
