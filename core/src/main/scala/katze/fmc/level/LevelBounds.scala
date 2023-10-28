package katze.fmc.level

import io.github.iltotore.iron.constraint.all.Positive
import io.github.iltotore.iron.{*, given}

trait LevelBounds[F[_], Level]:
  def height(level : Level): F[Int :| Positive]
  def minY(level : Level): F[Int]

  def maxY(level : Level): F[Int]
end LevelBounds

def height[F[_], Level](level: Level)(using bounds : LevelBounds[F, Level]): F[Int :| Positive] =
  bounds.height(level)
end height

def minY[F[_], Level](level: Level)(using bounds : LevelBounds[F, Level]): F[Int] =
  bounds.minY(level)
end minY

def maxY[F[_], Level](level: Level)(using bounds : LevelBounds[F, Level]): F[Int] = bounds.maxY(level)
