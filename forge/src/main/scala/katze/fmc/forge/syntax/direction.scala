package katze.fmc.forge.syntax

import katze.fmc.Direction as FmcDirection
import net.minecraft.core.Direction as VanilaDirection

object direction:
  given asVanilaDirection : Conversion[FmcDirection, VanilaDirection] =
    {
      case FmcDirection.East => VanilaDirection.EAST
      case FmcDirection.West => VanilaDirection.WEST
      case FmcDirection.North => VanilaDirection.NORTH
      case FmcDirection.South => VanilaDirection.SOUTH
      case FmcDirection.Up => VanilaDirection.UP
      case FmcDirection.Down => VanilaDirection.DOWN
    }
  
  given asFmcDirection : Conversion[VanilaDirection, FmcDirection] =
    {
      case VanilaDirection.DOWN => FmcDirection.Down
      case VanilaDirection.UP => FmcDirection.Up
      case VanilaDirection.NORTH => FmcDirection.North
      case VanilaDirection.SOUTH => FmcDirection.South
      case VanilaDirection.WEST => FmcDirection.West
      case VanilaDirection.EAST => FmcDirection.East
    }
end direction
    
