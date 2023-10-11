package fmc
package level

trait RedstoneView[F[_]]:
  def strongRedstonePower(level: Level, pos: BlockPos, direction: Direction) : F[Int]
  
  def receivedStrongRedstonePower(level: Level, pos: BlockPos): F[Int]
  
  def emittedRedstonePower(level: Level, pos: BlockPos, onlyFromGate : Boolean): F[Int]
  
  def emittedRedstonePower(level: Level, pos: BlockPos, direction: Direction): F[Int]
end RedstoneView
