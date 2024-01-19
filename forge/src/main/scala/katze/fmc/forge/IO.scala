package katze.fmc.forge

enum IO[T]:
  case Pure(value : T)
  case Dirt(value : () => T)
  case FlatMap[A, B](prev : IO[A], func : A => IO[B]) extends IO[B]
  
  @annotation.tailrec
  final def runUnsafe : T = this match
    case IO.FlatMap(prev, func) => func(prev.runUnsafe).runUnsafe
    case IO.Pure(value) => value
    case IO.Dirt(value) => value()
  end runUnsafe
end IO

object IO:
  def apply[T](value : => T) : IO[T] = IO.Dirt(() => value)