package fmc
package block

import block.state.Properties

final case class Block private[fmc](location : ResourceLocation, defaultProperties : Properties)
