package katze.fmc.forge.registry

import katze.fmc.block.BlockPrototype
import net.minecraft.world.level.block.Block

trait BlockImplementation[-F[_], +Level]:
  def implementBlock(prototype : BlockPrototype[F, Level]) : Block
end BlockImplementation

def implementBlock[F[_], Level](prototype : BlockPrototype[F, Level])(using I : BlockImplementation[F, Level]) : Block = I.implementBlock(prototype)