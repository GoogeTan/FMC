package me.zahara.fmc
package registry

import block.{Block, BlockPrototype}
import block.entity.BlockEntityPrototype
import data.Serializable

trait RegistrableBlock[F[_]]:
  def registerBlock(resourceLocation: ResourceLocation, block : BlockPrototype[F]) : F[Block]
end RegistrableBlock
