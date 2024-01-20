package katze.fmc.registry

import katze.fmc.ResourceLocation
import katze.fmc.creative.tab.{ CreativeModeTab, CreativeModeTabPrototype }

trait RegistrableCreativeTab[F[_]]:
  def register(resourceLocation: ResourceLocation, prototype : CreativeModeTabPrototype) : F[CreativeModeTab]
end RegistrableCreativeTab
