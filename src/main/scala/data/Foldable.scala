package fmc.data

trait Foldable[F[_]]:
  def foldLeft[T, K](container : F[T], initialValue : K)(compose : (K, T) => K) : K

  def foldRight[T, K](container: F[T], initialValue: K)(compose: (T, K) => K): K
end Foldable


def foldLeft[F[_], T, K](container: F[T], initialValue: K)(compose: (K, T) => K)(using foldable: Foldable[F]): K =
  foldable.foldLeft(container, initialValue)(compose)
end foldLeft

def foldRight[F[_], T, K](container: F[T], initialValue: K)(compose: (T, K) => K)(using foldable: Foldable[F]): K =
  foldable.foldRight(container, initialValue)(compose)
end foldRight

def foldMapL[F[_] : Foldable, T](container: F[T])(using monoid: Monoid[T]): T =
  foldLeft(container, monoid.empty)(monoid.combine)
end foldMapL

def foldMapR[F[_] : Foldable, T](container: F[T])(using monoid: Monoid[T]): T =
  foldRight(container, monoid.empty)(monoid.combine)
end foldMapR
