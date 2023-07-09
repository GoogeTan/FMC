package me.zahara.fmc
package syntax

import collection.{Dependent, DependentMap}

object dependendmap:
  export collection.TypeErasureDependentMap.isDependentMap
  
  extension [C, D <: Dependent](collection : C)
    def withValue(key: D)(value: key.Value)(using dm : DependentMap[C, D]): C =
      dm.withValue(collection, key)(value)
    end withValue
  
  
    def valueOf(key: D)(using dm : DependentMap[C, D]): Option[key.Value] =
      dm.valueOf(collection, key)
    end valueOf
  end extension
end dependendmap
