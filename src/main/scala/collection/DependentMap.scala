package me.zahara.fmc
package collection

trait DependentMap[C, D <: Dependent]:
  def withValue(collection : C, key : D)(value : key.Value) : C

  def valueOf(collection : C, key : D) : Option[key.Value]
end DependentMap