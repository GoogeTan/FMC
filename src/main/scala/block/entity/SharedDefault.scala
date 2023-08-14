package fmc
package block.entity

final case class SharedDefault[F[_], T](defaultValue : T, shared : SharedData[F, T])
