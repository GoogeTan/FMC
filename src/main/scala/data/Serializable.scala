package me.zahara.fmc
package data

import data.Pattern

trait Serializable[T]:
  def serialize(value : T) : NBT

  def deserialize(container : NBT) : Option[T]
end Serializable
