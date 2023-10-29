package katze.fmc.block.action

import katze.fmc.ResourceLocation
import katze.fmc.block.BlockRegistryEntry

trait BlockFromResourceLocation[F[_]]:
  def blockOf(location: ResourceLocation) : F[Option[BlockRegistryEntry]]
end BlockFromResourceLocation
