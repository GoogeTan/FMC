package me.zahara.fmc
package collection

trait DependentMap[C, F[_]]:
  def withValue[T](collection : C, key : F[T], value : T) : C

  def valueOf[T](collection : C, key : F[T]) : Option[T]
end DependentMap