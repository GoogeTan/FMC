package katze.fmc.registry

import katze.fmc.ResourceLocation
import katze.fmc.block.{ BlockRegistryEntry, BlockPrototype }
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.block.state.Property

trait RegistrableBlock[F[_], Level]:
  def registerBlock(resourceLocation: ResourceLocation, block : BlockPrototype[F, Level]) : F[BlockRegistryEntry]
end RegistrableBlock


def registerBlock[F[_], Level](resourceLocation: ResourceLocation, block: BlockPrototype[F, Level])(using reg : RegistrableBlock[F, Level]): F[BlockRegistryEntry] =
  reg.registerBlock(resourceLocation, block)
end registerBlock
