package fmc
package block

import block.state.{BlockState, Properties}

final case class Block private[fmc](location : ResourceLocation, defaultState : Properties)
