package katze.fmc.forge.registry

import katze.fmc.block.BlockPrototype
import katze.fmc.forge.IO
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

trait BlockImplementation[-F[_], +Level]:
  def implementBlock(prototype : BlockPrototype[F, Level]) : Block
end BlockImplementation

def implementBlock[F[_], Level](prototype : BlockPrototype[F, Level])(using I : BlockImplementation[F, Level]) : Block = I.implementBlock(prototype)

object BlockImplementation extends BlockImplementation[IO, Level]:
  override def implementBlock(prototype: BlockPrototype[IO, Level]): Block =
    new Block(BlockBehaviour.Properties.of())