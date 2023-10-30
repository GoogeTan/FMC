package katze.fmc.example

import katze.fmc.Direction.*
import katze.fmc.{ BlockPos, Direction, ResourceLocation }
import katze.fmc.block.state.*
import katze.fmc.block.*
import katze.fmc.data.*
import katze.fmc.syntax.show.{ javaBoolShow, showDirection }
import katze.fmc.data.*
import katze.fmc.example.PistonPushReaction.*
import katze.fmc.level.*
import katze.fmc.syntax.all.{ *, given }

import java.lang.Boolean.{ FALSE, TRUE }
import scala.collection.Set

val extendedProperty = setProperty("extended", Set(true, false))
val directionProperty = setProperty("direction", Direction.allValues.toSet)

def pistonBaseBlock[F[_] : Ap, Level](settings : BlockSettings) : BlockPrototype[F, Level] =
  blockPrototype(
    settings,
    noProperties ++ (extendedProperty -> false) ++ (directionProperty -> Direction.West)
  )
end pistonBaseBlock

def updatePistonBeingExtended[
                              F[_]
                                : Monad
                                : BlockPistonPushReaction,
                              Level
                                : RedstoneView[F, _]
                                : BlockView[F, _]
                                : LevelBounds[F, _]
                              ](level : Level, pos : BlockPos, state : BlockState) : F[Unit] =
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
end updatePistonBeingExtended

def tryExtendPiston[
                      F[_]
                        : Monad
                        : BlockPistonPushReaction,
                      Level
                        : BlockView[F, _]
                        : LevelBounds[F, _]
                    ](
                        level : Level,
                        pos : BlockPos,
                        direction : Direction
                     ) : F[Unit] =
  val targetPos = pos.relative(direction)
  for
    targetState <- blockStateAt(level, targetPos)
    canMove <- canPistonMoveBlockTowardsDirection(targetState, level, targetPos, direction, true, direction) // TODO доделать это чудо
  yield ()
end tryExtendPiston

def unextendPiston[F[_] , Level](level : Level, pos : BlockPos, direction : Direction) : F[Unit] = ???

def isPistonPowered[F[_] : Monad, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, side : Direction) : F[Boolean] =
  for
    sides <- isEmittingPowerAtSides(level, pos, Direction.allValues.filter(_ != side))
    hasPowerDown <-isEmittingRedstonePower(level, pos, Down)
    hasPowerAtTop <- isEmittingPowerAtSides(level, pos.up, Direction.allValues.filter(_ != Down)) // TODO Хорошо разобраться в алгосе и дать норм имена. А то тут не очевидно
  yield sides || hasPowerDown || hasPowerAtTop
end isPistonPowered

def isEmittingPowerAtSides[F[_] : Ap, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, sides : List[Direction]) : F[Boolean] =
  sides.traverse(dir => isEmittingRedstonePower(level, pos.relative(dir), dir)) >>* (_.contains(true))
end isEmittingPowerAtSides

def canPistonMoveBlockTowardsDirection[
                                        F[_]
                                          : Monad
                                          : BlockPistonPushReaction,
                                        Level
                                          : LevelBounds[F, _]
                                      ](
                                         targetsBlockState : BlockState,
                                         level : Level,
                                         targetPos : BlockPos,
                                         moveDirection : Direction,
                                         canPistonBreakBlocks : Boolean,
                                         pistonDirection : Direction
                                       ) : F[Boolean] =
  staysInWorldBounds(level, targetPos, moveDirection)
    && !isMadeOfObsidian(targetsBlockState)
    && !isUnbreakable(targetsBlockState)
    && !isExtendedPiston(targetsBlockState)
    && (isAir(targetsBlockState) || isBlockMovable(targetsBlockState, moveDirection, canPistonBreakBlocks, pistonDirection))
end canPistonMoveBlockTowardsDirection

private def isBlockMovable[F[_] : Monad : BlockPistonPushReaction](blockState : BlockState, direction: Direction, canBreak: Boolean, pistonDir: Direction) : F[Boolean] =
  blockPistonPushReaction(blockState.block) >>= {
    case PistonPushReaction.Block => pure(false)
    case PistonPushReaction.Destroy => pure(canBreak)
    case PistonPushReaction.PushOnly => pure(direction == pistonDir)
    case _ => !hasBlockEntity(blockState)
  }
end isBlockMovable

private def isExtendedPiston[F[_] : Monad : BlockPistonPushReaction](state : BlockState) : F[Boolean] =
  isPistonBlock(state) && valueFromState(state, extendedProperty).getOrElse(FALSE)
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