package katze.fmc.forge

enum IO[T]:
  case Pure(value : T)
  case Dirt(value : () => T)
  case FlatMap[A, B](prev : IO[A], func : A => IO[B]) extends IO[B]
end IO

