package katze.fmc.typing

type Inner[F[_], G[_], T] = F[G[T]]
