package katze.fmc

import katze.fmc.Direction
import katze.fmc.Direction.*

import scala.annotation.targetName

final case class BlockPos(x : Int, y : Int, z : Int):
  def relative(dir : Direction) : BlockPos =
    this + dir.offset
  end relative
  
  @targetName("sum")
  def +(other : BlockPos) : BlockPos = BlockPos(x + other.x, y + other.y, z + other.z)
  
  def up: BlockPos = relative(Up)
  def down: BlockPos = relative(Down)
  def east: BlockPos = relative(East)
  def west: BlockPos = relative(West)
  def north: BlockPos = relative(North)
  def south : BlockPos = relative(South)
end BlockPos
