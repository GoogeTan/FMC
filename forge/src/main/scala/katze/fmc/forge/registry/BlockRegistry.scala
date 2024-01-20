package katze.fmc.forge.registry

import katze.fmc.{ ModId, Path, ResourceLocation }
import katze.fmc.block.{ BlockPrototype, BlockRegistryEntry }
import katze.fmc.forge.IO
import katze.fmc.registry.RegistrableBlock
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.DeferredRegister

class BlockRegistry(registries : ModId => DeferredRegister[Block])(using BlockImplementation[IO, Level]) extends RegistrableBlock[IO, Level]:
  override def registerBlock(name: ResourceLocation, prototype: BlockPrototype[IO, Level]): IO[BlockRegistryEntry] =
    IO:
      registries(name.namespace).register(name.path, () => implementBlock(prototype))
      BlockRegistryEntry(name, prototype.defaultProperties)
  end registerBlock
end BlockRegistry
