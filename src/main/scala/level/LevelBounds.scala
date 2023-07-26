package me.zahara.fmc
package level

import cats.Applicative
import cats.syntax.all.{*, given}
import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.numeric.{Multiple, Positive}

trait LevelBounds[F[_]]:
  extension (level : Level)
    def height: F[Int :| Positive & Multiple[16]] // Может тут ограничение на делимость на 16 лишнее
    def minY: F[Int]

    def maxY(using Applicative[F]): F[Int] =
      minY.map2(height)(_ + _ - 1)
    end maxY

    def borderRadius: F[Int :| Positive]
  end extension
end LevelBounds

