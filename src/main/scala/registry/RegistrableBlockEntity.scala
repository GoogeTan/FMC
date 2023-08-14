package fmc
package registry

import block.entity.BlockEntityPrototype
import block.{Block, BlockPrototype}
import data.Serializable

trait RegistrableBlockEntity[F[_]]:
  def registerBlockWithEntity[State : Serializable](
    resourceLocation: ResourceLocation,
    prototype: BlockPrototype[F],
    blockEntityPrototype: BlockEntityPrototype[F, State]
  ): F[Block]
end RegistrableBlockEntity

def registerBlockWithEntity[F[_], State: Serializable](
  resourceLocation: ResourceLocation,
  prototype: BlockPrototype[F],
  blockEntityPrototype: BlockEntityPrototype[F, State]
)(using reg : RegistrableBlockEntity[F]): F[Block] =
  reg.registerBlockWithEntity(resourceLocation, prototype, blockEntityPrototype)
end registerBlockWithEntity
