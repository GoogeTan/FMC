package fmc
package collection

import typing.Id

type FPair[F[_], T] = FGPair[F, Id, T]

final case class FGPair[F[_], G[_], T](key : F[T], value : G[T])
