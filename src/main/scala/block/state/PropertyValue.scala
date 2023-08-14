package fmc
package block.state

final case class PropertyValue[T <: Comparable[T]](property: Property[T], value : T)
