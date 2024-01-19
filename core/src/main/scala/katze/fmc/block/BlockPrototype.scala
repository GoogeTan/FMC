package katze.fmc.block

import katze.fmc.BlockPos
import katze.fmc.block.state.{ BlockState, Properties, defaultStateOf }
import katze.fmc.data.*

final case class BlockPrototype[+F[_], -Level](
  settings                : BlockSettings,
  defaultProperties       : Properties,
  stateForPlacement       : PlacementState[F, Level],
  neighborUpdatedReaction : NeighborUpdatedReaction[F, Level]
)

def blockPrototype[F[_], Level](using Ap[F])(
                                                settings: BlockSettings,
                                                defaultProperties: Properties,
                                                neighborUpdatedReaction: NeighborUpdatedReaction[F, Level] = NoNeighborUpdatedReaction[F],
                                                stateForPlacement: PlacementState[F, Level] = DefaultPlacementState[F]
                                              ) : BlockPrototype[F, Level] =
  BlockPrototype(
    settings,
    defaultProperties,
    stateForPlacement,
    neighborUpdatedReaction
  )
end blockPrototype

@FunctionalInterface
trait NeighborUpdatedReaction[+F[_], -Level]:
  def reaction(self : BlockRegistryEntry, level : Level, pos : BlockPos, state : BlockState, from : BlockPos, fromBlockState : BlockState) : F[Unit]
end NeighborUpdatedReaction

class NoNeighborUpdatedReaction[F[_] : Ap] extends NeighborUpdatedReaction[F, Any]:
  override def reaction(self: BlockRegistryEntry, level: Any, pos: BlockPos, state: BlockState, from: BlockPos, fromBlockState: BlockState): F[Unit] = pure(())
end NoNeighborUpdatedReaction


@FunctionalInterface
trait PlacementState[+F[_], -Level]:
  def stateForPlacement(self : BlockRegistryEntry, level : Level, pos : BlockPos) : F[BlockState]
end PlacementState

class DefaultPlacementState[+F[_] : Ap] extends PlacementState[F, Any]:
  override def stateForPlacement(self: BlockRegistryEntry, level: Any, pos: BlockPos): F[BlockState] = pure(defaultStateOf(self))
end DefaultPlacementState
