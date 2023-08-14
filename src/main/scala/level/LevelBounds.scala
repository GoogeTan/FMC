package fmc
package level

import syntax.all.{*, given}

trait LevelBounds[F[_]]:
  def height(level : Level): F[Int :| Positive]
  def minY(level : Level): F[Int]

  def maxY(level : Level): F[Int]

  def borderRadius(level : Level): F[Int :| Positive]
end LevelBounds

def height[F[_]](level: Level)(using bounds : LevelBounds[F]): F[Int :| Positive] =
  bounds.height(level)
end height

def minY[F[_]](level: Level)(using bounds : LevelBounds[F]): F[Int] =
  bounds.minY(level)
end minY

def maxY[F[_]](level: Level)(using bounds : LevelBounds[F]): F[Int] = bounds.maxY(level)

def borderRadius[F[_]](level: Level)(using bounds : LevelBounds[F]): F[Int :| Positive] =
  bounds.borderRadius(level)
end borderRadius
