package katze.fmc.block

import katze.fmc.ResourceLocation
import katze.fmc.block.state.Properties

final case class BlockRegistryEntry private[fmc] (location : ResourceLocation, defaultProperties : Properties)
