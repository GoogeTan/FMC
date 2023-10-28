package katze.fmc.text

import katze.fmc.data.*
import katze.fmc.syntax.all.{*, given}

enum Text:
  case Pure(value : String)
  case Translate(key : String) // TODO Сделать тип только валидных ключей
  case Concat(components : List[Text])
end Text

def asString[F[_] : Ap : Translate](component: Text) : F[String] =
  component match
    case Text.Pure(value)        => pure(value)
    case Text.Translate(key)     => translate(key)                >>* (_.getOrElse(key))
    case Text.Concat(components) => components.traverse(asString) >>* foldMapL
  end match
end asString
