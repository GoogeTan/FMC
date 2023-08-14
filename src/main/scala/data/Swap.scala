package fmc.data

import fmc.data.{Ap, FMap}

trait Swap[F[_]]:
  def swap[G[_] : Ap, T](value : F[G[T]]) : G[F[T]]
end Swap

def traverse[F[_] : FMap, G[_] : Ap, T, B](value: F[T])(func: T => G[B])(using tr : Swap[F]): G[F[B]] =
  swap(fmap(value)(func))
end traverse

def swap[F[_], G[_] : Ap, T](value: F[G[T]])(using tr : Swap[F]): G[F[T]] =
  tr.swap(value)
end swap
