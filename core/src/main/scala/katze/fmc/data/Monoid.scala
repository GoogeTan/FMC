package katze.fmc.data

trait Monoid[T] extends Semigroup[T]:
  def empty : T
end Monoid
