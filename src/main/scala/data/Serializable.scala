package me.zahara.fmc
package data

trait Serializable[T]:
  def serialize(value : T) : NBT

  def deserialize(container : NBT) : Option[T]
end Serializable
