package me.zahara.fmc
package text

import cats.{Applicative, Foldable, Monoid}
import syntax.cats.{*, given}

enum Component:
  case Text(value : String)
  case Translate(key : String) // TODO Сделать тип только валидных ключей
  case Concat(components : List[Component])
end Component

def asString[F[_] : Applicative](component: Component)(using tr : Translate[F]) : F[String] =
  component match
    case Component.Text(value) => value.pure[F]
    case Component.Translate(key) => tr.translate(key).map(_.getOrElse(key))
    case Component.Concat(components) => components.traverse(asString).map(_.combineAll)
  end match
end asString
