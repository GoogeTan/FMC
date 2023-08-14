package fmc
package block

import block.state.{BlockState, Properties}
import level.Level

final case class BlockPrototype[F[_]](
  settings                : BlockSettings,
  defaultProperties       : Properties,
  neighborUpdatedReaction : (self : Block, level : Level, pos : BlockPos, from : BlockPos) => F[Unit],
  stateForPlacement       : (self : Block, level : Level, pos : BlockPos) => F[BlockState]
)
