package katze.fmc.block.state

import katze.fmc.collection.*
import katze.fmc.data.*

import scala.reflect.{ ClassTag, classTag }
import katze.fmc.syntax.show.given

import scala.collection.Set

final case class Property[T : Ordering](name : String, values : BiMap[T, String])(using val clazz : ClassTag[T]):
  override def toString: String =
    katze.fmc.syntax.show.longPropertyShow.show(this)
  end toString
end Property


def intProperty(name : String, from : Int, to : Int) : Property[Integer] =
  setProperty(name, (from to to).map(a => a.asInstanceOf[Integer]).toSet)
end intProperty

def setProperty[T : ClassTag : Show : Ordering](name : String, possibleValues : scala.collection.Set[T]) : Property[T] =
  Property(
    name = name,
    values = ListBiMap(
      possibleValues.map(i => (i, show(i))).toList
    ).get
  )
end setProperty

def isValidFor[T](property: Property[T], value : T) : Boolean =
  property.values.containsKey(value)
end isValidFor
