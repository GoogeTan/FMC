package me.zahara.fmc


type DependentMapT[F[_]] = [C] =>> DependentMap[C, F]

trait DependentMap[C, F[_]]:
  def withValue[T](collection : C, key : F[T], value : T) : C

  def valueOf[T](collection : C, key : F[T]) : Option[T]
end DependentMap
