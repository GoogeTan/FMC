package katze.fmc.block

import katze.fmc.block.material.Material

final case class BlockSettings(
                                meterial : Material,
                                resistance: Float = .0,
                                hardness: Float = .0,
                                toolRequired: Boolean = false,
                                randomTicks: Boolean = false,
                                slipperiness: Float = .0,
                                velocityMultiplier: Float = .0,
                                jumpVelocityMultiplier: Float = .0,
                                opaque: Boolean = false,
                                isAir: Boolean = false,
                                burnable: Boolean = false
                              )
