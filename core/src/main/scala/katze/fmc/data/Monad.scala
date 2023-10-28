package katze.fmc.data

import katze.fmc.data.*

trait Monad[F[_]] extends FMap[F] with MMap[F] with Ap[F]:
  override def apply[A, B](value: F[A])(func: F[A => B]): F[B] =
    mmap(func)(f => fmap(value)(f))
  end apply
end Monad

object Monad:
  def apply[F[_]](using m : Monad[F]): Monad[F] = m
end Monad
