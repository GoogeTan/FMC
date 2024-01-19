package katze.fmc.forge.level

import katze.fmc.BlockPos
import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block.state.{ BlockState, Properties }
import katze.fmc.data.*
import katze.fmc.forge.IO
import katze.fmc.forge.syntax.blockstate.{ asFmcBlockState, asVanilaState }
import katze.fmc.level.BlockAccess
import net.minecraft.world.level.Level
import katze.fmc.forge.syntax.all.{*, given }
import io.github.iltotore.iron.{*, given }

given blockAccessImpl : BlockAccess[IO, Level] with
  override def blockAt(level: Level, pos: BlockPos): IO[BlockRegistryEntry] =
    blockStateAt(level, pos) >>* (_.block)
  
  override def blockPropertiesAt(level: Level, pos: BlockPos): IO[Properties] =
    blockStateAt(level, pos) >>* (_.properties)
  end blockPropertiesAt
  
  override def blockStateAt(level: Level, pos: BlockPos): IO[BlockState] =
    IO:
      asFmcBlockState(level.getBlockState(pos))
  end blockStateAt
  
  override def updateBlockAt(level: Level, position: BlockPos, withNewState: BlockState): IO[BlockState] =
    for
      old <- blockStateAt(level, position)
      _ <- updateBlockAt_(level, position, withNewState)
    yield old
  end updateBlockAt
  
  override def updateBlockAt_(level: Level, position: BlockPos, withNewState: BlockState): IO[Unit] =
    IO:
      level.setBlockAndUpdate(position, asVanilaState(withNewState))
  end updateBlockAt_
end blockAccessImpl
