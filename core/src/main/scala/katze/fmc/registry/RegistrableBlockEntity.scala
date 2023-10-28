package katze.fmc.registry

import katze.fmc.ResourceLocation
import katze.fmc.block.{ Block, BlockPrototype }
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.block.state.Property
import katze.fmc.data.*

trait RegistrableBlockEntity[
  F[_],
  Level,
  ClientLevel <: Level,
  ServerLevel <: Level
]:
  def registerBlockWithEntity[State : Serializable](
    resourceLocation: ResourceLocation,
    prototype: BlockPrototype[F, Level],
    blockEntityPrototype: BlockEntityPrototype[F, Level, ClientLevel, ServerLevel, State]
  ): F[Block]
end RegistrableBlockEntity

def registerBlockWithEntity[
  F[_],
  Level,
  State: Serializable,
  ClientLevel <: Level,
  ServerLevel <: Level
](
  resourceLocation: ResourceLocation,
  prototype: BlockPrototype[F, Level],
  blockEntityPrototype: BlockEntityPrototype[F, Level, ClientLevel, ServerLevel, State]
)(using reg : RegistrableBlockEntity[F, Level, ClientLevel, ServerLevel]): F[Block] =
  reg.registerBlockWithEntity(resourceLocation, prototype, blockEntityPrototype)
end registerBlockWithEntity
