package katze.fmc.forge.syntax

import katze.fmc.BlockPos as FmcBlockPos
import net.minecraft.core.BlockPos as VanilaBlockPos

object blockpos:
  given asVanilaBlockPos: Conversion[FmcBlockPos, VanilaBlockPos] = pos => VanilaBlockPos(pos.x, pos.y, pos.z)
  given asFmcBlockPos : Conversion[VanilaBlockPos, FmcBlockPos] = pos => FmcBlockPos(pos.getX, pos.getY, pos.getZ)
end blockpos
