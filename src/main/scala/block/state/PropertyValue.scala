package fmc
package block.state

case class PropertyValue[T <: Comparable[T]](property: Property[T], value : T)
