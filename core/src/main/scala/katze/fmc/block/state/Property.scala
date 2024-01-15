package katze.fmc.block.state

import katze.fmc.collection.*
import katze.fmc.data.*

import scala.reflect.{ ClassTag, classTag }
import katze.fmc.syntax.show.given

import scala.collection.Set

sealed trait Property[T](using val clazz : Class[T]):
  val name : String
  val values : BiMap[T, String]
end Property

final case class CaseProperty[T <: Comparable[T] : Class](
                                                            override val name : String,
                                                            override val values : BiMap[T, String]
                                                          ) extends Property[T]:
  override def toString: String =
    katze.fmc.syntax.show.longPropertyShow.show(this)
  end toString
end CaseProperty

def intProperty(name : String, from : Int, to : Int) : Property[Integer] =
  setProperty(name, (from to to).map(Integer.valueOf).toSet)
end intProperty

def setProperty[T <: Comparable[T] : Show : Ordering : ClassTag](name : String, possibleValues : scala.collection.Set[T]) : Property[T] =
  CaseProperty(
    name = name,
    values = ListBiMap(
      possibleValues.map(i => (i, show(i))).toList
    ).get
  )(using classTag[T].runtimeClass.asInstanceOf)
end setProperty

def isValidFor[T](property: Property[T], value : T) : Boolean =
  property.values.containsKey(value)
end isValidFor
