package katze.fmc.collection

final case class ListBiMap[K, V] private (values : Iterable[(K, V)]) extends BiMap[K, V]:
  override def keyOf(value: V): Option[K] =
    values.find(_._2 == value).map(_._1)
  
  override def valueOf(key: K): Option[V] =
    values.find(_._1 == key).map(_._2)

  override def iterator: Iterator[(K, V)] = values.iterator
end ListBiMap

object ListBiMap:
  def apply[K, V](pairs : Iterable[(K, V)]) : Option[ListBiMap[K, V]] =
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

  private def areUnique[T](values: Iterable[T]): Boolean = values.toSet.size == values.size

  def unsafe[K, V](values : Iterable[(K, V)]) : ListBiMap[K, V] = apply(values).get
  
end ListBiMap

