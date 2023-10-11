package fmc
package block

import block.state.{ BlockState, Properties, Property }
import level.Level

final case class BlockPrototype[F[_]](
  settings                : BlockSettings,
  defaultProperties       : Properties,
  neighborUpdatedReaction : NeighborUpdatedReaction[F],
  stateForPlacement       : PlacementState[F]
)

@FunctionalInterface
trait NeighborUpdatedReaction[F[_]]:
  def reaction(self : Block, level : Level, pos : BlockPos, state : BlockState, from : BlockPos, fromBlockState : BlockState) : F[Unit]
end NeighborUpdatedReaction

@FunctionalInterface
trait PlacementState[F[_]]:
  def stateForPlacement(self : Block, level : Level, pos : BlockPos) : F[BlockState]
end PlacementState
