package katze.fmc.data

import scala.annotation.targetName

trait MMap[F[_]]:
  def mmap[A, B](value : F[A])(func : A => F[B]) : F[B]
end MMap

def mmap[F[_], A, B](value : F[A])(func : A => F[B])(using mmp : MMap[F]) : F[B] =
  mmp.mmap(value)(func)
end mmap

def mmapi[F[_] : Monad, G[_] : Monad : Swap, A, B](value : F[G[A]])(func : A => F[G[B]]) : F[G[B]] =
  katze.fmc.syntax.monad.inner.innerMonad[F, G].mmap(value)(func)
end mmapi

extension[F[_], T] (value: F[T])(using monad: Monad[F])
  @targetName("mmapOperator")
  def >>=[B](func: T => F[B]): F[B] =
    monad.mmap(value)(func)
  end >>=
end extension
