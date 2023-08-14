package fmc
package collection

trait BiMap[C, +K, +V]:
  def keyOf[U >: V](collection : C, value : U) : Option[K]
  def valueOf[U >: K](collection : C, key : U) : Option[V]
end BiMap

def keyOf[C, K, V](collection: C, value: V)(using biMap: BiMap[C, K, V]): Option[K] =
  biMap.keyOf(collection, value)
end keyOf

def valueOf[C, K, V](collection: C, key: K)(using biMap: BiMap[C, K, V]): Option[V] =
  biMap.valueOf(collection, key)
end valueOf
