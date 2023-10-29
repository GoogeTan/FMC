package katze.fmc.forge.syntax

import katze.fmc.block.BlockRegistryEntry as FmcBlock
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block as VanilaBlock
import net.minecraftforge.registries.ForgeRegistries
import resource.{*, given}
import blockstate.asProperties

object block:
  def asFmcBlock(block : VanilaBlock) : FmcBlock =
    val location = ForgeRegistries.BLOCKS.getKey(block)
    FmcBlock(location, asProperties(block.defaultBlockState()))
  end asFmcBlock
  
  def asVanilaBlock(fmcBlock: FmcBlock) : VanilaBlock =
    ForgeRegistries.BLOCKS.getValue(fmcBlock.location)
  end asVanilaBlock
end block
