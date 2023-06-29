package me.zahara.fmc

trait BiMap[C, K, V]:
  def keyOf(collection : C, value : V) : Option[K]
  def valueOf(collection : C, key : K) : Option[V]
end BiMap
