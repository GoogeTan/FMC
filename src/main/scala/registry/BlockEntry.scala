package me.zahara.fmc
package registry

import block.BlockPrototype
import block.entity.BlockEntityPrototype

case class BlockEntry[
  F[_],
  Level,
  ClientLevel <: Level,
  ServerLevel <: Level
](
    resourceLocation: ResourceLocation,
    blockPrototype: BlockPrototype[F, Level],
    blockEntity : Option[BlockEntityEntry[F, ?, Level, ClientLevel, ServerLevel]]
) // TODO Добавить модель

case class BlockEntityEntry[
  F[_],
  State,
  Level,
  ClientLevel <: Level,
  ServerLevel <: Level
](
    prototype : BlockEntityPrototype[F, State, Level, ClientLevel, ServerLevel]
) // TODO добавить модель