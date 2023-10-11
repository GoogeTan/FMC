package fmc.data

trait Swap[F[_]]:
  def swap[G[_] : Ap, T](value : F[G[T]]) : G[F[T]]
end Swap

/*
def traverse[F[_] : FMap, G[_] : Ap, T, B](value: F[T])(func: T => G[B])(using tr : Swap[F]): G[F[B]] =
  swap(fmap(value)(func))
end traverse*/

extension [F[_] : FMap : Swap, T](value : F[T])
  def traverse[G[_] : Ap, B](func: T => G[B]): G[F[B]] =
    swap(fmap(value)(func))
  end traverse
end extension

def swap[F[_], G[_] : Ap, T](value: F[G[T]])(using tr : Swap[F]): G[F[T]] =
  tr.swap(value)
end swap
