package fmc
package registry

import block.{Block, BlockPrototype}
import block.entity.BlockEntityPrototype
import data.Serializable

trait RegistrableBlock[F[_]]:
  def registerBlock(resourceLocation: ResourceLocation, block : BlockPrototype[F]) : F[Block]
end RegistrableBlock


def registerBlock[F[_]](resourceLocation: ResourceLocation, block: BlockPrototype[F])(using reg : RegistrableBlock[F]): F[Block] =
  reg.registerBlock(resourceLocation, block)
end registerBlock
