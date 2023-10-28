package katze.fmc.forge.syntax

import katze.fmc.BlockPos as FmcBlockPos
import net.minecraft.core.BlockPos as VanilaBlockPos

object blockpos:
  given asVanilaBlockPos: Conversion[FmcBlockPos, VanilaBlockPos] = ???
  
  given asFmcBlockPos : Conversion[VanilaBlockPos, FmcBlockPos] = ???
end blockpos
