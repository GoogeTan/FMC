package katze.fmc.forge.level

import io.github.iltotore.iron.constraint.all.*
import katze.fmc.BlockPos
import katze.fmc.forge.IO
import katze.fmc.level.LevelBounds
import net.minecraft.world.level.Level
import io.github.iltotore.iron.{*, given}
import katze.fmc.forge.syntax.io.{*, given}
import katze.fmc.forge.syntax.blockpos.{*, given}

given levelBoundsImpl: LevelBounds[IO, Level] with
  override def height(level: Level): IO[Int :| Positive] =
    IO:
      val height : Int = level.dimensionType().height()
      height.refine
  end height
  
  override def maxY(level: Level): IO[Int] =
    IO:
      level.getMaxBuildHeight
  end maxY
  
  override def minY(level: Level): IO[Int] =
    IO:
      level.dimensionType().minY()
  end minY
  
  override def isWithinBorderBounds(level: Level, pos: BlockPos): IO[Boolean] =
    IO:
      level.getWorldBorder.isWithinBounds(pos)
  end isWithinBorderBounds
end levelBoundsImpl
