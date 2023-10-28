package katze.fmc.block

import katze.fmc.ResourceLocation
import katze.fmc.block.state.Properties

final case class Block private[fmc](location : ResourceLocation, defaultProperties : Properties)
