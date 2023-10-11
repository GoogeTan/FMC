package fmc
package registry

import block.{ Block, BlockPrototype }
import block.entity.BlockEntityPrototype
import data.Serializable

import fmc.block.state.Property

trait RegistrableBlock[F[_]]:
  def registerBlock[K <: Property[?]](resourceLocation: ResourceLocation, block : BlockPrototype[F, K]) : F[Block]
end RegistrableBlock


def registerBlock[F[_], K <: Property[?]](resourceLocation: ResourceLocation, block: BlockPrototype[F, K])(using reg : RegistrableBlock[F]): F[Block] =
  reg.registerBlock(resourceLocation, block)
end registerBlock
