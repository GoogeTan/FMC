package me.zahara.fmc
package collection

case class TypeErasureDependentMap[D <: Dependent] private(private val values : Map[D, Any])

object TypeErasureDependentMap:
  def asList[D <: Dependent](map : TypeErasureDependentMap[D]) : List[DependentPair[D]] =
    map.values.toList.map { (a, b) => DependentPair(a.asInstanceOf)(b.asInstanceOf) }
  end asList

  def empty[D <: Dependent] : TypeErasureDependentMap[D] =
    TypeErasureDependentMap(Map())
  end empty
  
  given isDependentMap[D <: Dependent] : DependentMap[TypeErasureDependentMap[D], D] with
    override def valueOf(collection: TypeErasureDependentMap[D], key: D): Option[key.Value] =
      collection.values.get(key).map(_.asInstanceOf)
    end valueOf

    override def withValue(collection: TypeErasureDependentMap[D], key: D)(value: key.Value): TypeErasureDependentMap[D] =
      new TypeErasureDependentMap(collection.values + (key -> value))
    end withValue
  end isDependentMap
end TypeErasureDependentMap
