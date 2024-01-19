package katze.fmc.forge.level

import katze.fmc.data.Monad
import katze.fmc.level.{ BlockAccess, BlockView, LevelBounds, RedstoneSignal, RedstoneView, updateBlockAt }
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.*
import katze.fmc.block.state.*
import katze.fmc.forge.IO
import net.minecraft.world.level.{ Level, SignalGetter }
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.data.*

given redstoneViewImpl: RedstoneView[IO, SignalGetter] with
  override def strongRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] =
    IO:
      level.getDirectSignal(pos, direction).refine
  
  override def receivedStrongRedstonePower(level: SignalGetter, pos: BlockPos): IO[RedstoneSignal] =
    IO:
      level.getBestNeighborSignal(pos).refine
  end receivedStrongRedstonePower
  
  override def emittedRedstonePower(level: SignalGetter, pos: BlockPos, direction: Direction): IO[RedstoneSignal] =
    IO:
      level.getDirectSignal(pos, direction).refine
  end emittedRedstonePower
end redstoneViewImpl
