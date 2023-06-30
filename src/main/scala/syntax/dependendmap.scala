package me.zahara.fmc
package syntax

import collection.DependentMap

object dependendmap:
  extension [C](collection : C)
    def withValue[F[_], T](key: F[T], value: T)(using dm : DependentMap[C, F]): C =
      dm.withValue(collection, key, value)
    end withValue
  
  
    def valueOf[F[_], T](key: F[T])(using dm : DependentMap[C, F]): Option[T] =
      dm.valueOf(collection, key)
    end valueOf
  end extension
end dependendmap
