package fmc
package syntax

import collection.{Dependent, DependentPair, FGPair, FPair, Split}

object split:
  given fpair[F[_], G[_]] : Split[FGPair[F, G, ?], F, G] with
    override def apply[K](value: FGPair[F, G, ?], func: [U] => (F[U], G[U]) => K) : K =
      func(value.key, value.value)
    end apply
  end fpair

  type Dep[T] = Dependent { type Value = T }
  type Id[X] = X

  given dpair[D <: Dependent]: Split[DependentPair[D], Dep, Id] with
    override def apply[K](value: DependentPair[D], func: [U] => (Dep[U], U) => K): K =
      func(value.key, value.value)
    end apply
  end dpair
end split
