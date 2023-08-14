package fmc
package block.state

import collection.{ListBiMap, containsKey}

import scala.reflect.ClassTag

final case class Property[T <: Comparable[T]](name : String, values : ListBiMap[T, String])(using val clazz : Class[T]):
  type Value = T
end Property

def isValidFor[T <: Comparable[T]](property: Property[T], value : T) : Boolean =
  containsKey(property.values, value)
end isValidFor
