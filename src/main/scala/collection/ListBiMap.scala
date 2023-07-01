package me.zahara.fmc
package collection

final case class ListBiMap[K, V] private (values : List[(K, V)])

def containsKey[K, V](listBiMap: ListBiMap[K, V], key : K) : Boolean =
  listBiMap.values.contains { (currentKey : K, _ : V) => key == currentKey }
end containsKey

def containsValue[K, V](listBiMap: ListBiMap[K, V], value : V) : Boolean =
  listBiMap.values.contains { (_ : K, currentValue : V) => value == currentValue }
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

  given given_lbim[K, V]: BiMap[ListBiMap[K, V], K, V] with
    override def keyOf(collection: ListBiMap[K, V], value: V): Option[K] =
      collection.values.find(_._2 == value).map(_._1)
    end keyOf

    override def valueOf(collection: ListBiMap[K, V], key: K): Option[V] =
      collection.values.find(_._1 == key).map(_._2)
    end valueOf

  end given_lbim

  private def areUnique[T](values: List[T]): Boolean = values.toSet.size == values.size
end ListBiMap

