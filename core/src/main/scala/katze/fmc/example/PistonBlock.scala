package katze.fmc.example

import katze.fmc.Direction.*
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.state.*
import katze.fmc.block.*
import katze.fmc.data.*
import katze.fmc.syntax.show.{ javaBoolShow, showDirection }
import katze.fmc.data.{ Ap, Monad }
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

def isPushable[
                F[_]
                  : Monad
                  : BlockPistonPushReaction,
                Level
                  : LevelBounds[F, _]
              ](
                 state : BlockState,
                 level : Level,
                 pos : BlockPos,
                 direction : Direction,
                 canBreak : Boolean,
                 pistonDir : Direction,
                 piston : Block,
                 stickyPiston : Block
               ) : F[Boolean] =
  
  map4(inBounds(level, pos, direction), isAir(state), madeOfObsidian(state), hasBlockEntity(state))(
    inBounds => isAir => isMadeOfObsidian => hasBlockEntity =>
        if !inBounds then
          false
        else if isAir then
          true
        else if isMadeOfObsidian then
          false
        else if state.block != piston && state.block != stickyPiston then
          ??? // TODO закончить кусок с случаем, если у не поршень, а любой блок
        else if valueFromState(state, extendedProperty).get then
          false
        else
          !hasBlockEntity
  )
end isPushable

private def inBounds[F[_] : Ap, Level : LevelBounds[F, _]](level : Level, pos : BlockPos, direction : Direction) : F[Boolean] =
  map2(minY(level), maxY(level)):
    minY => maxY =>
      if pos.y < minY || maxY < pos.y then
        false
      else if direction == Down && pos.y == minY then
        false
      else if direction == Up && pos.y == maxY then
        false
      else
        true
      end if
end inBounds