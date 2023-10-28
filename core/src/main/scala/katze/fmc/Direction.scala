package katze.fmc

import katze.fmc.Direction.*

enum Direction(val axis : Axis, val orientation: AxisOrientation, val offset : BlockPos) extends Comparable[Direction]:
  case East  extends Direction(Axis.Y, AxisOrientation.Positive, BlockPos( 1,  0,  0))
  case West  extends Direction(Axis.Y, AxisOrientation.Negative, BlockPos(-1,  0,  0))
  case North extends Direction(Axis.Z, AxisOrientation.Negative, BlockPos( 0,  0, -1))
  case South extends Direction(Axis.Z, AxisOrientation.Positive, BlockPos( 0,  0,  1))
  case Up    extends Direction(Axis.Y, AxisOrientation.Positive, BlockPos( 0,  1,  0))
  case Down  extends Direction(Axis.Y, AxisOrientation.Negative, BlockPos( 0, -1,  0))

  override def compareTo(o: Direction): Int =
    this.ordinal.compareTo(o.ordinal)
  end compareTo

  override def toString: String =
    super.toString.toLowerCase
  end toString

end Direction

object Direction:
  def allValues : List[Direction] = Direction.values.toList
  def horizontalValues : List[Direction] = List(West, East, North, South)
  
  enum Axis:
    case X
    case Y
    case Z
  end Axis
  
  enum AxisOrientation:
    case Positive
    case Negative
  end AxisOrientation
end Direction

