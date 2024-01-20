package katze.fmc.forge.registry

import katze.fmc.creative.tab.CreativeModeTabPrototype
import net.minecraft.world.item.CreativeModeTab as VanilaCreativeModeTab


trait CreativeTabImplementation:
  def implementTab(prototype: CreativeModeTabPrototype) : VanilaCreativeModeTab
end CreativeTabImplementation


def implementTab(prototype: CreativeModeTabPrototype)(using T : CreativeTabImplementation): VanilaCreativeModeTab = T.implementTab(prototype)