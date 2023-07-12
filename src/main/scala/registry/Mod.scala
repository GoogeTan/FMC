package me.zahara.fmc
package registry

import block.BlockPrototype
import item.ItemPrototype

import io.github.iltotore.iron.:|

type ToRegisterList[T] = List[(ResourceLocation, T)]

case class Mod[
  F[_],
  Level,
  ServerLevel <: Level,
  ClientLevel <: Level
](
  name   : String :| ResourceNamespace,
  items  : F[ToRegisterList[ItemPrototype]],
  blocks : F[ToRegisterList[BlockEntry[F, Level, ClientLevel, ServerLevel]]],
)
