package katze.fmc.example

import katze.fmc.Direction.*
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.state.*
import katze.fmc.block.*
import katze.fmc.data.*
import katze.fmc.syntax.show.{ javaBoolShow, showDirection }
import katze.fmc.data.{ Ap, Monad }
import katze.fmc.example.PistonPushReaction.*
import katze.fmc.level.*
import katze.fmc.syntax.all.{ *, given }

import scala.collection.Set

val extendedProperty = setProperty[java.lang.Boolean]("extended", Set(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE))
val directionProperty = setProperty("direction", Direction.allValues.toSet)

def pistonBaseBlock[F[_] : Ap, Level](settings : BlockSettings) : BlockPrototype[F, Level] =
  blockPrototype(
    settings,
    noProperties ++ (extendedProperty -> false) ++ (directionProperty -> Direction.West)
  )
end pistonBaseBlock

def updateBeingExtended[F[_] : Monad : BlockPistonPushReaction, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, state : BlockState) : F[Unit] =
  val pistonOrientation = valueFromState(state, directionProperty).get
  val extended = valueFromState(state, extendedProperty).get
  for
    shouldBeExtended <- isPistonPowered(level, pos, pistonOrientation)
    _ <-
      if (shouldBeExtended && !extended)
        tryExtendPiston(level, pos, pistonOrientation)
      else if (!shouldBeExtended && extended)
        unextendPiston(level, pos, pistonOrientation)
      else
        nothingToDo
  yield ()
end updateBeingExtended

def tryExtendPiston[F[_], Level](level : Level, pos : BlockPos, direction : Direction) : F[Unit] = ???
def unextendPiston[F[_] , Level](level : Level, pos : BlockPos, direction : Direction) : F[Unit] = ???

def isPistonPowered[F[_] : Monad, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, side : Direction) : F[Boolean] =
  for
    sides <- hasRedstonePowerAtSides(level, pos, Direction.allValues.filter(_ != side))
    hasPowerDown <-isEmittingRedstonePower(level, pos, Down)
    hasPowerTop <- hasRedstonePowerAtSides(level, pos.up, Direction.allValues.filter(_ != Down)) // TODO Хорошо разобраться в алгосе и дать норм имена.
  yield sides || hasPowerDown || hasPowerTop
end isPistonPowered

def hasRedstonePowerAtSides[F[_] : Ap, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, sides : List[Direction]) : F[Boolean] =
  sides.traverse(dir => isEmittingRedstonePower(level, pos.relative(dir), dir)) >>* (_.contains(true))
end hasRedstonePowerAtSides

def canPistonMoveBlockTowardsDirection[
                                        F[_]
                                          : Monad
                                          : BlockPistonPushReaction,
                                        Level
                                          : LevelBounds[F, _]
                                      ](
                                         piston: Block,
                                         stickyPiston: Block,
                                         targetsBlockState : BlockState,
                                         level : Level,
                                         targetPos : BlockPos,
                                         direction : Direction,
                                         canBreak : Boolean,
                                         pistonDirection : Direction
                                       ) : F[Boolean] =
  staysInWorldBounds(level, targetPos, direction)
    && !isMadeOfObsidian(targetsBlockState)
    && !isUnbreakable(targetsBlockState)
    && !isExtendedPiston(targetsBlockState, piston, stickyPiston)
    && (isAir(targetsBlockState) || isBlockMovable(targetsBlockState, direction, canBreak, pistonDirection))
end canPistonMoveBlockTowardsDirection

private def isBlockMovable[F[_] : Monad : BlockPistonPushReaction](blockState : BlockState, direction: Direction, canBreak: Boolean, pistonDir: Direction) : F[Boolean] =
  blockPistonPushReaction(blockState.block) >>= {
    case PistonPushReaction.Block => pure(false)
    case PistonPushReaction.Destroy => pure(canBreak)
    case PistonPushReaction.PushOnly => pure(direction == pistonDir)
    case _ => !hasBlockEntity(blockState)
  }
end isBlockMovable

private def isExtendedPiston[F[_] : Ap](state : BlockState, pistons : Block*) : Boolean =
  pistons.contains(state.block) && valueFromState(state, extendedProperty).getOrElse(java.lang.Boolean.FALSE)
end isExtendedPiston

private def staysInWorldBounds[F[_] : Ap, Level : LevelBounds[F, _]](level : Level, pos : BlockPos, pushDirection : Direction) : F[Boolean] =
  map3(minY(level), maxY(level), isWithinBorderBounds(level, pos)):
    minY => maxY => insideBorder =>
      insideBorder
        && !(pos.y < minY)
        && !(pos.y > maxY)
        && !(pushDirection == Down && pos.y == minY)
        && !(pushDirection == Up && pos.y == maxY)
end staysInWorldBounds