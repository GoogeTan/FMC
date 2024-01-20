package katze.fmc.forge.registry

import katze.fmc.creative.tab
import katze.fmc.creative.tab.CreativeModeTabPrototype
import katze.fmc.forge.IO
import katze.fmc.{ ModId, ResourceLocation }
import katze.fmc.registry.RegistrableCreativeTab
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.registries.DeferredRegister

class CreativeTabRegistry(
                            val registry : ModId => DeferredRegister[CreativeModeTab]
                          )(using CreativeTabImplementation) extends RegistrableCreativeTab[IO]:
  override def register(resourceLocation: ResourceLocation, prototype: CreativeModeTabPrototype): IO[tab.CreativeModeTab] =
    IO:
      registry(resourceLocation.namespace).register(resourceLocation.path, () => implementTab(prototype))
      tab.CreativeModeTab(resourceLocation)
  end register
end CreativeTabRegistry
