package katze.fmc.registry

import katze.fmc.{ Path, ResourceLocation }
import katze.fmc.block.{ BlockPrototype, BlockRegistryEntry }

trait RegistrableBlock[F[_], Level]:
  def registerBlock(resourceLocation: ResourceLocation, block : BlockPrototype[F, Level]) : F[BlockRegistryEntry]
end RegistrableBlock


def registerBlock[F[_], Level](resourceLocation: ResourceLocation, block: BlockPrototype[F, Level])(using reg : RegistrableBlock[F, Level]): F[BlockRegistryEntry] =
  reg.registerBlock(resourceLocation, block)
end registerBlock
