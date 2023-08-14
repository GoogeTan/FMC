package fmc
package syntax

import data.*

object swap:
  given Swap[Option] with
    override def swap[G[_] : Ap, T](value: Option[G[T]]): G[Option[T]] =
      value match
        case Some(value) => fmap(value)(Some.apply)
        case None => pure(None)
    end swap
  end given

  given Swap[List] with
    override def swap[G[_] : Ap, T](value: List[G[T]]): G[List[T]] =
      value match
        case ::(head, next) => map2(head, swap(next))(::.apply)
        case Nil => pure(Nil)
    end swap
  end given
end swap
