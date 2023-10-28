package katze.fmc.syntax

import katze.fmc.data.Monoid

object monoid:
  given Monoid[String] with
    override def empty: String = ""

    override def combine(left: String, right: String): String = left + right
  end given

  given listMonoid[T] : Monoid[List[T]] with
    override def empty: List[T] = Nil
    override def combine(left: List[T], right: List[T]): List[T] = left ++ right
  end listMonoid
end monoid
