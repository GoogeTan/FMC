package fmc
package collection

import syntax.bimap.given

final case class ListBiMap[+K, +V] private (values : List[(K, V)])

def containsKey[K, V](listBiMap: ListBiMap[K, V], key : K) : Boolean =
  valueOf(listBiMap, key).isDefined
end containsKey

def containsValue[K, V](listBiMap: ListBiMap[K, V], value : V) : Boolean =
  keyOf(listBiMap, value).isDefined
end containsValue

object ListBiMap:
  def apply[K, V](pairs : List[(K, V)]) : Option[ListBiMap[K, V]] =
    val keys = pairs.map(_._1)
    val values = pairs.map(_._2)
    if areUnique(keys) && areUnique(values) then
      Some(new ListBiMap(pairs))
    else
      None
    end if
  end apply

  def apply[K, V](pairs: (K, V)*): Option[ListBiMap[K, V]] =
    apply(pairs.toList)
  end apply

  private def areUnique[T](values: List[T]): Boolean = values.toSet.size == values.size
end ListBiMap

