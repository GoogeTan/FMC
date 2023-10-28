package katze.fmc.registry

import katze.fmc.ResourceLocation
import katze.fmc.block.{ Block, BlockPrototype }
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.block.state.Property

trait RegistrableBlock[F[_], Level]:
  def registerBlock(resourceLocation: ResourceLocation, block : BlockPrototype[F, Level]) : F[Block]
end RegistrableBlock


def registerBlock[F[_], Level](resourceLocation: ResourceLocation, block: BlockPrototype[F, Level])(using reg : RegistrableBlock[F, Level]): F[Block] =
  reg.registerBlock(resourceLocation, block)
end registerBlock
