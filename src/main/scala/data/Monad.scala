package fmc.data

import fmc.data.{Ap, FMap, MMap}

trait Monad[F[_]] extends FMap[F] with MMap[F] with Ap[F]:
  override def apply[A, B](value: F[A])(func: F[A => B]): F[B] =
    mmap(func)(f => fmap(value)(f))
  end apply
end Monad

