package me.zahara.fmc
package collection

case class LinkedHashMap[K, V] private (list: List[(K, V)]):
  def erase(key : K) : LinkedHashMap[K, V] =
    LinkedHashMap(list.filter(_._1 != key))
  end erase

  def withValue(key : K, value : V) : LinkedHashMap[K, V] =
    LinkedHashMap((key, value) :: erase(key).list)
  end withValue
end LinkedHashMap

object LinkedHashMap:
  def empty[K, V]: LinkedHashMap[K, V] = LinkedHashMap[K, V](List())
end LinkedHashMap
