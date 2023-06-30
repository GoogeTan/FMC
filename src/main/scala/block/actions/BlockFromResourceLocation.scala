package me.zahara.fmc
package block.actions

import block.Block

trait BlockFromResourceLocation[F[_]]:
  def blockOf(location: ResourceLocation) : F[Option[Block]]
end BlockFromResourceLocation
