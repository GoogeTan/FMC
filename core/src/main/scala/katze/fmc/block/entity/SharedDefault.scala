package katze.fmc.block.entity

final case class SharedDefault[+F[_], -ServerLevel, T](defaultValue : T, shared : SharedData[F, ServerLevel, T])
