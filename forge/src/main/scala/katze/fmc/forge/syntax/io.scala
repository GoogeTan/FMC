package katze.fmc.forge.syntax

import katze.fmc.data.Monad
import katze.fmc.level.{ GetBlock, LevelBounds, PropertiesAtPos, RedstoneSignal, RedstoneView }
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.*
import katze.fmc.block.state.*
import katze.fmc.forge.IO
import net.minecraft.world.level.{ Level, SignalGetter }
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.data.*
import net.minecraft.world.level.block.piston.PistonBaseBlock

object io:
  given Monad[IO] with
    override def mmap[A, B](value: IO[A])(func: A => IO[B]): IO[B] = IO.FlatMap(value, func)
    
    override def pure[T](value: T): IO[T] = IO.Pure(value)
  end given
  
  given GetBlock[IO, Level] with
    override def blockAt(level: Level, pos: BlockPos): IO[Block] =
      blockStateAt(level, pos) >>* (_.block)
    
    override def blockPropertiesAt(level: Level, pos: BlockPos): IO[Properties] =
      blockStateAt(level, pos) >>* (_.properties)
    end blockPropertiesAt
    
    override def blockStateAt(level: Level, pos: BlockPos): IO[BlockState] =
      IO.Dirt(() => {asFmcBlockState(level.getBlockState(pos))})
    end blockStateAt
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
  end given
  
  def updateBlockAt(level : Level, pos : BlockPos, f : BlockState => Option[BlockState]) : IO[Option[BlockState]] =
    IO.Dirt(() =>
      val oldState = asFmcBlockState(level.getBlockState(pos))
      f(oldState) match
        case Some(newState) =>
          level.setBlockAndUpdate(pos, asVanila(newState))
          Some(oldState)
        case None => None
      end match
    )
  end updateBlockAt
  
  given PropertiesAtPos[IO, Level] with
    override def updatePropertyAt[T <: Comparable[T]](level: Level, pos: BlockPos, property: Property[T], newValue: T): IO[Option[T]] =
      updateBlockAt(
        level,
        pos,
        withValue(_, property, newValue)
      ) >>* (_.flatMap(valueFromState[T](_ : BlockState, property)))
    end updatePropertyAt
  end given
  
  given RedstoneView[IO, SignalGetter] with
    override def strongRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] = ???
    
    override def receivedStrongRedstonePower(level: SignalGetter, pos: BlockPos): IO[RedstoneSignal] =
      IO.Dirt(() => level.getBestNeighborSignal(pos).refine)
    end receivedStrongRedstonePower
    
    override def emittedRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] =
      IO.Dirt(() => level.getDirectSignal(pos, direction).refine)
    end emittedRedstonePower
  end given
end io
