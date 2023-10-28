package katze.fmc.block.action

import katze.fmc.ResourceLocation
import katze.fmc.block.Block

trait BlockFromResourceLocation[F[_]]:
  def blockOf(location: ResourceLocation) : F[Option[Block]]
end BlockFromResourceLocation
