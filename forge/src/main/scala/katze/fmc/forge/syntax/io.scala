package katze.fmc.forge.syntax

import katze.fmc.data.Monad
import katze.fmc.level.{ BlockAccess, BlockView, LevelBounds, RedstoneSignal, RedstoneView, updateBlockAt }
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.*
import katze.fmc.block.state.*
import katze.fmc.forge.IO
import net.minecraft.world.level.{ Level, SignalGetter }
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.data.*

object io:
  given Monad[IO] with
    override def mmap[A, B](value: IO[A])(func: A => IO[B]): IO[B] = IO.FlatMap(value, func)
    
    override def pure[T](value: T): IO[T] = IO.Pure(value)
  end given
  export katze.fmc.forge.level.blockAccessImpl
  export katze.fmc.forge.level.levelBoundsImpl
  export katze.fmc.forge.level.redstoneViewImpl
end io
