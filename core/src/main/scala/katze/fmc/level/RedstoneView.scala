package katze.fmc.level

import io.github.iltotore.iron.{*, given}
import io.github.iltotore.iron.constraint.numeric.Interval.{ ClosedOpen, OpenClosed }
import katze.fmc.data.{ FMap, fmap }
import katze.fmc.{ BlockPos, Direction }


/**
 * Сигнал красного камня. Принимает значения [0, 16)
 */
type RedstoneSignal = Int :| ClosedOpen[0, 16]

trait RedstoneView[+F[_], -Level]:
  def strongRedstonePower(level: Level, pos: BlockPos, direction: Direction) : F[RedstoneSignal]
  
  /**
   *
   */
  def receivedStrongRedstonePower(level: Level, pos: BlockPos): F[RedstoneSignal]
  
  /**
   * Сила сигнала красного камня у блока на стороне side.
   */
  def emittedRedstonePower(level: Level, pos: BlockPos, side: Direction): F[RedstoneSignal]
end RedstoneView

def strongRedstonePower[F[_], Level](level: Level, pos: BlockPos, direction: Direction)(using r : RedstoneView[F, Level]): F[RedstoneSignal] =
  r.strongRedstonePower(level, pos, direction)

/**
 *
 */
def receivedStrongRedstonePower[F[_], Level](level: Level, pos: BlockPos)(using r : RedstoneView[F, Level]): F[RedstoneSignal] =
  r.receivedStrongRedstonePower(level, pos)

/**
 * Сила сигнала красного камня у блока на стороне side.
 */
def emittedRedstonePower[F[_], Level](level: Level, pos: BlockPos, side: Direction)(using r : RedstoneView[F, Level]): F[RedstoneSignal] =
  r.emittedRedstonePower(level, pos, side)

def isEmittingRedstonePower[F[_] : FMap, Level](level: Level, pos: BlockPos, side: Direction)(using r : RedstoneView[F, Level]): F[Boolean] =
  fmap(r.emittedRedstonePower(level, pos, side))((a : RedstoneSignal) => a > 0)