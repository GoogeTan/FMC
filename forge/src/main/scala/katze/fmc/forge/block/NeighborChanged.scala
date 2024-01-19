package katze.fmc.forge.block

import katze.fmc.block.*
import katze.fmc.forge.IO
import katze.fmc.forge.syntax.block.asFmcBlock
import katze.fmc.forge.syntax.blockstate.asFmcBlockState
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import katze.fmc.forge.syntax.all.{ *, given }

trait NeighborChanged(neighborUpdatedReaction : NeighborUpdatedReaction[IO, Level]) extends Block:
  override def neighborChanged(state : BlockState, level : Level, pos : BlockPos, block : Block, from : BlockPos, todo : Boolean): Unit =
    neighborUpdatedReaction.reaction(
      asFmcBlock(this),
      level,
      pos, asFmcBlockState(state),
      from, asFmcBlockState(level.getBlockState(from))
    ).runUnsafe
    super.neighborChanged(state, level, pos, block, from, todo)
  end neighborChanged
end NeighborChanged
