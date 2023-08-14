package fmc.data

trait Semigroup[T]:
  def combine(left : T, right : T) : T
end Semigroup

