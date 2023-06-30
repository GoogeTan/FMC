package me.zahara.fmc
package syntax

import collection.BiMap

object bimap:
  extension [C](collection : C)
    def keyOf[K, V](value: V)(using biMap: BiMap[C, K, V]): Option[K] = biMap.keyOf(collection, value)
    def valueOf[K, V](key: K)(using biMap: BiMap[C, K, V]): Option[V] = biMap.valueOf(collection, key)
  end extension
end bimap
