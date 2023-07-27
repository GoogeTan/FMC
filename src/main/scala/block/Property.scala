package me.zahara.fmc
package block

import collection.{ListBiMap, containsKey}

case class Property[T](name : String, values : ListBiMap[T, String])

def isValidFor[T](property: Property[T], value : T) : Boolean =
  containsKey(property.values, value)
end isValidFor
