package me.zahara.fmc
package registry

import block.{Block, BlockPrototype}
import block.entity.BlockEntityPrototype
import data.Serializable

trait RegistrableBlock[F[_]]:
  def registerBlockWithEntity[
    State : Serializable,
    Level,
    ClientLevel <: Level,
    ServerLevel <: Level
  ](
     resourceLocation: ResourceLocation,
     prototype: BlockPrototype[F, Level],
     blockEntityPrototype: BlockEntityPrototype[F, State, Level, ClientLevel, ServerLevel]
   ): F[Block]

  def registerBlock[Level](resourceLocation: ResourceLocation, block : BlockPrototype[F, Level]) : F[Block]
end RegistrableBlock
