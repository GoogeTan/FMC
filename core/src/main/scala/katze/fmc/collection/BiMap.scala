package katze.fmc.collection

trait BiMap[K, V] extends Iterable[(K, V)]:
  def keyOf(value : V) : Option[K]
  def valueOf(key : K) : Option[V]
  
  def containsKey(key: K): Boolean =
    valueOf(key).isDefined
  end containsKey
  
  def containsValue(value: V): Boolean =
    keyOf(value).isDefined
  end containsValue

end BiMap