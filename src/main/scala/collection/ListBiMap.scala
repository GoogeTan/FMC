package me.zahara.fmc
package collection

case class ListBiMap[K, V] private (values : List[(K, V)])

object ListBiMap:
  def apply[K, V](pairs : List[(K, V)]) : Option[ListBiMap[K, V]] =
    if areUnique(pairs.map(_._1)) && areUnique(pairs.map(_._2)) then
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
end ListBiMap

private def areUnique[T](values : List[T]) : Boolean = values.toSet.size == values.size
