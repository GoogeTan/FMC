package me.zahara.fmc
package collection

case class TypeErasureDepandentMap[F[_]] private (values : Map[Any, Any])

object TypeErasureDepandentMap:
  given isDependentMap[F[_]] : DependentMap[TypeErasureDepandentMap[F], F] with
    override def valueOf[T](collection: TypeErasureDepandentMap[F], key: F[T]): Option[T] =
      collection.values.get(key).map(_.asInstanceOf)
    end valueOf

    override def withValue[T](collection: TypeErasureDepandentMap[F], key: F[T], value: T): TypeErasureDepandentMap[F] =
      new TypeErasureDepandentMap(collection.values + (key -> value))
    end withValue
  end isDependentMap
end TypeErasureDepandentMap