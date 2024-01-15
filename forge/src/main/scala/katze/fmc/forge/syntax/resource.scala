package katze.fmc.forge.syntax

import net.minecraft.resources.ResourceLocation as VanilaResourceLocation
import katze.fmc.syntax.iron.{ *, given }
import katze.fmc.ResourceLocation as FmcResourceLocation
import net.minecraft.world.level.block.piston.PistonBaseBlock

object resource:
  given asVanilaResourceLocation: Conversion[FmcResourceLocation, VanilaResourceLocation] = value => VanilaResourceLocation(value.namespace, value.path)
  given asFmcResourceLocation: Conversion[VanilaResourceLocation, FmcResourceLocation] = value => FmcResourceLocation(value.getNamespace.refine, value.getPath.refine)
end resource
