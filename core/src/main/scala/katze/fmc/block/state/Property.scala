package katze.fmc.block.state

import katze.fmc.collection.*
import katze.fmc.data.*
import scala.reflect.{ ClassTag, classTag }
import katze.fmc.syntax.show.given
final case class Property[T <: Comparable[T]](name : String, values : BiMap[T, String])(using val clazz : Class[T])

def intProperty(name : String, from : Int, to : Int) : Property[Integer] =
  setProperty(name, (from to to).map(a => a.asInstanceOf[Integer]).toSet)
end intProperty

def setProperty[T <: Comparable[T] : ClassTag : Show](name : String, possibleValues : scala.collection.Set[T]) : Property[T] =
  Property(
    name = name,
    values = ListBiMap(
      possibleValues.map(i => (i, show(i))).toList
    ).get
  )(using classTag[T].runtimeClass.asInstanceOf)
end setProperty


def isValidFor[T <: Comparable[T]](property: Property[T], value : T) : Boolean =
  property.values.containsKey(value)
end isValidFor
