package me.zahara.fmc
package block

import collection.FPair

import cats.{Eval, Foldable}

case class Properties private[block] (private val values : Map[Property[?], Any]) extends Iterable[FPair[Property, ?]]:

  def valueOf[T](property: Property[T]): Option[T] =
    values.get(property).map(_.asInstanceOf)
  end valueOf

  def withValueFor[T](property: Property[T], value: T): Properties =
    Properties(values.updated(property, value))
  end withValueFor

  override def iterator : Iterator[FPair[Property, ?]] = values.toList.map((key : Property[?], value : Any) => FPair(key, value.asInstanceOf)).iterator
end Properties

given Foldable[Iterable] with
  override def foldLeft[A, B](fa: Iterable[A], b: B)(f: (B, A) => B): B =
    fa.foldLeft(b)(f)
  end foldLeft

  override def foldRight[A, B](fa: Iterable[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
    fa.foldRight(lb)(f)
  end foldRight
end given


def noProperties : Properties = Properties(Map())