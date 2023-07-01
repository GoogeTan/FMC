package me.zahara.fmc
package collection

case class TypeErasureDependentMap[F[_]] private(private val values : Map[Any, Any])

object TypeErasureDependentMap:
  def empty[F[_]] : TypeErasureDependentMap[F] =
    TypeErasureDependentMap(Map())
  end empty
  
  given isDependentMap[F[_]] : DependentMap[TypeErasureDependentMap[F], F] with
    override def valueOf[T](collection: TypeErasureDependentMap[F], key: F[T]): Option[T] =
      collection.values.get(key).map(_.asInstanceOf)
    end valueOf

    override def withValue[T](collection: TypeErasureDependentMap[F], key: F[T], value: T): TypeErasureDependentMap[F] =
      new TypeErasureDependentMap(collection.values + (key -> value))
    end withValue
  end isDependentMap
end TypeErasureDependentMap