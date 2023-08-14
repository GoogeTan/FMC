package fmc
package syntax

import collection.{BiMap, ListBiMap}

object bimap:
  given given_lbim[K, V]: BiMap[ListBiMap[K, V], K, V] with
    override def keyOf[U >: V](collection: ListBiMap[K, V], value: U): Option[K] =
      collection.values.find(_._2 == value).map(_._1)
    end keyOf

    override def valueOf[U >: K](collection: ListBiMap[K, V], key: U): Option[V] =
      collection.values.find(_._1 == key).map(_._2)
    end valueOf
  end given_lbim
end bimap
