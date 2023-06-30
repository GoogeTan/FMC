package me.zahara.fmc
package block

trait PropertiesOfBlock[F[_]]:
  def properties(block : ResourceLocation) : F[Option[Properties]]
end PropertiesOfBlock
