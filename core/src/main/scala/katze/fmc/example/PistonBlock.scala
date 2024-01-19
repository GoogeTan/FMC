package katze.fmc.example

import katze.fmc.Direction.*
import katze.fmc.block.*
import katze.fmc.block.action.*
import katze.fmc.block.state.*
import katze.fmc.data.*
import katze.fmc.example.PistonPushReaction.*
import katze.fmc.level.*
import katze.fmc.syntax.all.{ *, given }
import katze.fmc.{ BlockPos, Direction }

def pistonBaseBlock[F[_] : Ap, Level](
                                        settings : BlockSettings,
                                        extendedProperty : Property[Boolean],
                                        directionProperty : Property[Direction]
                                      ) : BlockPrototype[F, Level] =
  blockPrototype(
    settings,
    noProperties ++ (extendedProperty -> false) ++ (directionProperty -> Direction.West)
  )
end pistonBaseBlock

def updatePistonBeingExtended[
                              F[_]
                                : Monad
                                : PistonBlockTypes
                                : BlockTypes,
                              Level
                                : RedstoneView[F, _]
                                : BlockView[F, _]
                                : LevelBounds[F, _]
                              ](
                                level : Level,
                                pos : BlockPos,
                                state : BlockState,
                                extendedProperty : Property[Boolean],
                                directionProperty : Property[Direction]
                              )(using T : Throws[F, String]) : F[Unit] =
  for
    pistonOrientation <- T.expect(valueFromState(state, directionProperty), "updatePistonBeingExtended: The piston must have an direction property.")
    extended <- T.expect(valueFromState(state, extendedProperty), "updatePistonBeingExtended: The piston must have an extension property.")
    shouldBeExtended <- isPistonPowered(level, pos, pistonOrientation)
    _ <-
      if (shouldBeExtended && !extended)
        tryExtendPiston(level, pos, pistonOrientation, extendedProperty)
      else if (!shouldBeExtended && extended)
        unextendPiston(level, pos, pistonOrientation)
      else
        nothingToDo
  yield ()
end updatePistonBeingExtended

def tryExtendPiston[
                      F[_]
                        : Monad
                        : PistonBlockTypes
                        : BlockTypes,
                      Level
                        : BlockView[F, _]
                        : LevelBounds[F, _]
                    ](
                        level : Level,
                        pos : BlockPos,
                        direction : Direction,
                        extendedProperty : Property[Boolean]
                      ) : F[Unit] =
  val targetPos = pos.relative(direction)
  for
    targetState <- blockStateAt(level, targetPos)
    canMove <- canPistonMoveBlockTowardsDirection(targetState, level, targetPos, direction, true, direction, extendedProperty) // TODO доделать это чудо
  yield ()
end tryExtendPiston

def unextendPiston[F[_] , Level](level : Level, pos : BlockPos, direction : Direction) : F[Unit] = ???

def isPistonPowered[F[_] : Monad, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, side : Direction) : F[Boolean] =
  isEmittingRedstonePowerAtSides(level, pos, Direction.allValues.filter(_ != side))
    || isEmittingRedstonePower(level, pos, Down)
    || isEmittingRedstonePowerAtSides(level, pos.up, Direction.allValues.filter(_ != Down)) // TODO проверить читаемость и поправить
end isPistonPowered

def isEmittingRedstonePowerAtSides[F[_] : Ap, Level : RedstoneView[F, _]](level : Level, pos : BlockPos, sides : List[Direction]) : F[Boolean] =
  sides.traverse(dir => isEmittingRedstonePower(level, pos.relative(dir), dir)) >>* (_.contains(true))
end isEmittingRedstonePowerAtSides

def canPistonMoveBlockTowardsDirection[
                                        F[_]
                                          : Monad
                                          : PistonBlockTypes
                                          : BlockTypes,
                                        Level
                                          : LevelBounds[F, _]
                                      ](
                                          targetsBlockState : BlockState,
                                          level : Level,
                                          targetPos : BlockPos,
                                          moveDirection : Direction,
                                          canPistonBreakBlocks : Boolean,
                                          pistonDirection : Direction,
                                          extendedProperty : Property[Boolean]
                                        ) : F[Boolean] =
  staysInWorldBounds(level, targetPos, moveDirection)
    && !isMadeOfObsidian(targetsBlockState)
    && !isUnbreakable(targetsBlockState)
    && !isExtendedPiston(targetsBlockState, extendedProperty)
    && (isAir(targetsBlockState) || isBlockMovable(targetsBlockState, moveDirection, canPistonBreakBlocks, pistonDirection))
end canPistonMoveBlockTowardsDirection

private def isBlockMovable[F[_] : Monad : PistonBlockTypes : BlockTypes](blockState : BlockState, direction: Direction, canBreak: Boolean, pistonDir: Direction) : F[Boolean] =
  blockPistonPushReaction(blockState.block) >>= {
    case PistonPushReaction.Block => pure(false)
    case PistonPushReaction.Destroy => pure(canBreak)
    case PistonPushReaction.PushOnly => pure(direction == pistonDir)
    case _ => !hasBlockEntity(blockState)
  }
end isBlockMovable

private def isExtendedPiston[F[_] : Monad : PistonBlockTypes](state : BlockState, extendedProperty : Property[Boolean]) : F[Boolean] =
  isPistonBlock(state) && valueFromState(state, extendedProperty).getOrElse(false)
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