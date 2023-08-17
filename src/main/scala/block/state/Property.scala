package fmc
package block.state

import collection.{ListBiMap, containsKey}

import scala.reflect.{ClassTag, classTag}
import data.*
import syntax.show.given
final case class Property[T <: Comparable[T]](name : String, values : ListBiMap[T, String])(using val clazz : Class[T])

def intProperty(name : String, from : Int, to : Int) : Property[Integer] =
  setProperty(name, (from to to).map(a => a.asInstanceOf[Integer]).toSet)
end intProperty

def setProperty[T <: Comparable[T] : ClassTag : Show](name : String, possibleValues : Set[T]) : Property[T] =
  Property(
    name = name,
    values = ListBiMap(
      possibleValues.map(i => (i, show(i))).toList
    ).get
  )(using classTag[T].runtimeClass.asInstanceOf)
end setProperty


def isValidFor[T <: Comparable[T]](property: Property[T], value : T) : Boolean =
  containsKey(property.values, value)
end isValidFor
