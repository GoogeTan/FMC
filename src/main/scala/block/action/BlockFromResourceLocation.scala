package fmc
package block.action

import block.Block

trait BlockFromResourceLocation[F[_]]:
  def blockOf(location: ResourceLocation) : F[Option[Block]]
end BlockFromResourceLocation
