package katze.fmc.forge.syntax

import katze.fmc.ResourceLocation
import katze.fmc.forge.IO
import katze.fmc.block.*
import katze.fmc.syntax.all.{*, given }
import katze.fmc.forge.syntax.all.{*, given }
import katze.fmc.forge.syntax.block.asFmcBlock
import katze.fmc.registry.RegistrableBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraftforge.registries.ForgeRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.{ BlockBehaviour, BlockState }
import resource.asVanilaResourceLocation

object registry:
  given RegistrableBlock[IO, Level] = (location : ResourceLocation, prototype : BlockPrototype[IO, Level]) =>
    IO:
      ForgeRegistries.BLOCKS.register(asVanilaResourceLocation(location), VanilaBlock(prototype))
      BlockRegistryEntry(location, prototype.defaultProperties)
  end given
  
  private def VanilaBlock(prototype: BlockPrototype[IO, Level]) : Block =
    new Block(properties(prototype.settings)):
      override def neighborChanged(state : BlockState, level : Level, pos : BlockPos, block : Block, from : BlockPos, todo : Boolean): Unit =
        prototype.neighborUpdatedReaction.reaction(
          asFmcBlock(this),
          level,
          pos, asFmcBlockState(state),
          from, asFmcBlockState(level.getBlockState(from))
        ).runUnsafe
        super.neighborChanged(state, level, pos, block, from, todo)
      end neighborChanged
  end VanilaBlock
    
  private def properties(settings: BlockSettings) : BlockBehaviour.Properties = ???
end registry
