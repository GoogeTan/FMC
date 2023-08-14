package fmc.data

import fmc.data.Semigroup

trait Monoid[T] extends Semigroup[T]:
  def empty : T
end Monoid
