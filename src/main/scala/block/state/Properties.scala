package fmc
package block.state

import collection.{FGPair, FPair}

final case class Properties private[block] (private[block] val values : Map[Property[?], Any]) extends Iterable[PropertyValue[?]]:
  override def iterator : Iterator[PropertyValue[?]] =
    values
      .toList
      .map(
        (key : Property[?], value : Any) =>
          PropertyValue[key.Value](key, value.asInstanceOf[key.Value])
      )
      .iterator
end Properties

def foldP[R](properties: Properties, initial : R)(func : [T <: Comparable[T]] => (R, Property[T], T) => R) : R =
  properties.foldLeft(initial)((res, propertyValue) => func(res, propertyValue.property, propertyValue.value))
end foldP


def valueOf[T <: Comparable[T]](properties: Properties, property: Property[T]) : Option[T] =
  properties.values.get(property).map(_.asInstanceOf)
end valueOf

def withValueFor[T <: Comparable[T]](properties: Properties, property: Property[T], value: T) : Properties =
  Properties(properties.values.updated(property, value))
end withValueFor


def noProperties : Properties = Properties(Map())