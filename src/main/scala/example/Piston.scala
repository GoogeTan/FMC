package fmc
package example

import block.state.{ *, given }
import block.{ *, given }
import collection.ListBiMap
import data.*
import level.{ *, given }
import syntax.all.{ *, given }

val pistonFaceProperty = Property[Direction]("direction", ListBiMap(Direction.values.map(dir => (dir, dir.toString)).toList).get)(using classOf)

def piston[F[_] : Monad : GetBlock](settings: BlockSettings) = BlockPrototype[F](
  settings = settings,
  defaultProperties = noProperties ++ (pistonFaceProperty, Direction.Down),
  neighborUpdatedReaction = onNeighborChanged,
  stateForPlacement = ???
)


def onNeighborChanged[F[_] : Monad : GetBlock](self : Block, level: Level, pos : BlockPos, state : BlockState, from : BlockPos, fromBlockState : BlockState) : F[Unit] =
  for
    state : BlockState <- blockStateAt(level, pos)
    
    
  yield ()
end onNeighborChanged
  
def shouldExtend[F[_] : Monad : RedstoneView](self : Block, level: Level, pos: BlockPos, state: BlockState) : F[Boolean] =
  val pistonFace = valueOf(state.properties, pistonFaceProperty).get
  ???
  
def hasPower[F[_] : Monad](level: Level, pos: BlockPos, pistonFace : Direction)(using rw : RedstoneView[F]) : F[Boolean] =
  Direction
    .values
    .toList
    .filter(_ != pistonFace)
    .traverse(dir => rw.emittedRedstonePower(level, pos.offset(dir), dir)) >>* (_.exists(_ > 0))
end hasPower
