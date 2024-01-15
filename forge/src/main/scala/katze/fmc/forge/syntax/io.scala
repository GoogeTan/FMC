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
  
  given BlockAccess[IO, Level] with
    override def blockAt(level: Level, pos: BlockPos): IO[BlockRegistryEntry] =
      blockStateAt(level, pos) >>* (_.block)
    
    override def blockPropertiesAt(level: Level, pos: BlockPos): IO[Properties] =
      blockStateAt(level, pos) >>* (_.properties)
    end blockPropertiesAt
    
    override def blockStateAt(level: Level, pos: BlockPos): IO[BlockState] =
      IO.Dirt(() => {asFmcBlockState(level.getBlockState(pos))})
    end blockStateAt
    
    override def updateBlockAt(level: Level, position: BlockPos, withNewState: BlockState): IO[BlockState] =
      for
        old <- blockStateAt(level, position)
        _ <- updateBlockAt_(level, position, withNewState)
      yield old
    end updateBlockAt
    
    override def updateBlockAt_(level: Level, position: BlockPos, withNewState: BlockState): IO[Unit] =
      IO.Dirt(() => level.setBlockAndUpdate(position, asVanila(withNewState)))
    end updateBlockAt_
  end given
  
  given LevelBounds[IO, Level] with
    override def height(level: Level): IO[Int :| Positive] =
      IO.Dirt(() => level.dimensionType().height())
    end height
    
    override def maxY(level: Level): IO[Int] =
      IO.Dirt(() => level.getMaxBuildHeight)
    end maxY
    
    override def minY(level: Level): IO[Int] =
      IO.Dirt(() => level.dimensionType().minY())
    end minY
    
    override def isWithinBorderBounds(level: Level, pos: BlockPos): IO[Boolean] =
      IO.Dirt(() => level.getWorldBorder.isWithinBounds(pos))
    end isWithinBorderBounds
  end given

  given RedstoneView[IO, SignalGetter] with
    override def strongRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] =
      IO.Dirt(() => level.getDirectSignal(pos, direction).refine)
    
    override def receivedStrongRedstonePower(level: SignalGetter, pos: BlockPos): IO[RedstoneSignal] =
      IO.Dirt(() => level.getBestNeighborSignal(pos).refine)
    end receivedStrongRedstonePower
    
    override def emittedRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] =
      IO.Dirt(() => level.getDirectSignal(pos, direction).refine)
    end emittedRedstonePower
  end given
end io
