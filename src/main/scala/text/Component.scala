package fmc
package text

import data.*
import syntax.all.{*, given}
import syntax.iron.{*, given}

enum Component:
  case Text(value : String)
  case Translate(key : String) // TODO Сделать тип только валидных ключей
  case Concat(components : List[Component])
end Component

def asString[F[_] : Ap : Translate](component: Component) : F[String] =
  component match
    case Component.Text(value)        => pure(value)
    case Component.Translate(key)     => translate(key)                 >>* (_.getOrElse(key))
    case Component.Concat(components) => traverse(components)(asString) >>* foldMapL
  end match
end asString
