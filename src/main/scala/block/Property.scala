package me.zahara.fmc
package block

import collection.{Dependent, ListBiMap, containsKey}

case class Property[T <: Comparable[T]](name : String, values : ListBiMap[T, String]) extends Dependent:
  override type Value = T
end Property

def isValidFor[T <: Comparable[T]](property: Property[T], value : T) : Boolean =
  containsKey(property.values, value)
end isValidFor
