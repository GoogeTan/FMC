package katze.fmc
package typing

type Inner[F[_], G[_], T] = F[G[T]]
