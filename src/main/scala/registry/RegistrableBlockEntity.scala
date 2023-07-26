package me.zahara.fmc
package registry

import block.{Block, BlockPrototype}
import block.entity.BlockEntityPrototype
import data.Serializable

import me.zahara.fmc.level.Level

trait RegistrableBlockEntity[F[_]]:
  def registerBlockWithEntity[State : Serializable](
     resourceLocation: ResourceLocation,
     prototype: BlockPrototype[F],
     blockEntityPrototype: BlockEntityPrototype[F, State]
   ): F[Block]
end RegistrableBlockEntity
