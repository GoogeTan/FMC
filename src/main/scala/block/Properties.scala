package me.zahara.fmc
package block

import collection.TypeErasureDependentMap.asList
import collection.{DependentPair, TypeErasureDependentMap}
import syntax.dependendmap.{*, given}

case class Properties private[block] (private val values :  TypeErasureDependentMap[Property[?]]):

  def valueOf[T <: Comparable[T]](property: Property[T]): Option[T] =
    values.valueOf(property.asInstanceOf[Property[?]]).map(_.asInstanceOf[T])
  end valueOf

  def withValueFor[T <: Comparable[T]](property: Property[T], value: T): Properties =
    Properties(values.withValue(property.asInstanceOf[Property[?]])(value.asInstanceOf))
  end withValueFor

  private def iterator : Iterator[DependentPair[Property[?]]] = asList(values).iterator

  def mapT[K](func : [T <: Comparable[T]] => (Property[T], T) => K) : List[K] =
    iterator.map { pair =>
      func[pair.key.Value](pair.key, pair.value)
    }.toList
  end mapT

  def foldT[B](initial : B)(func : [T <: Comparable[T]] => (B, Property[T], T) => B) : B =
    iterator.foldRight(initial) { (pair, result) =>
      func(result, pair.key, pair.value)
    }
  end foldT


  def forEachT(func: [T <: Comparable[T]] => (Property[T], T) => Unit): Unit =
    iterator.foreach { pair =>
      func[pair.key.Value](pair.key, pair.value)
    }
end Properties



def noProperties : Properties = Properties(TypeErasureDependentMap.empty)