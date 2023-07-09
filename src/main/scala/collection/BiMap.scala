package me.zahara.fmc
package collection

trait BiMap[C, +K, +V]:
  def keyOf[U >: V](collection : C, value : U) : Option[K]
  def valueOf[U >: K](collection : C, key : U) : Option[V]
end BiMap
