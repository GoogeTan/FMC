package fmc

final case class BlockPos(x : Int, y : Int, z : Int):
  def offset(dir : Direction) : BlockPos = ???
