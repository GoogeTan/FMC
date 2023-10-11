package fmc
package block

import block.state.{ BlockState, Properties, Property }
import level.Level

final case class BlockPrototype[F[_], AllTheProperties <: Property[?]](
  settings                : BlockSettings,
  defaultProperties       : Properties,
  neighborUpdatedReaction : NeighborUpdatedReaction[F, AllTheProperties],
  stateForPlacement       : PlacementState[F]
)

@FunctionalInterface
trait NeighborUpdatedReaction[F[_], AllTheProperties <: Property[?]]:
  def reaction(self : Block, level : Level, pos : BlockPos, state : BlockState, from : BlockPos, fromBlockState : BlockState) : F[Unit]
end NeighborUpdatedReaction

@FunctionalInterface
trait PlacementState[F[_]]:
  def stateForPlacement(self : Block, level : Level, pos : BlockPos) : F[BlockState]
end PlacementState
