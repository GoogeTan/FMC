package katze.fmc.forge.registry

import net.minecraft.world.item.Item
import katze.fmc.item.*

trait ItemImplementation:
  def implementItem(prototype: ItemPrototype): Item
end ItemImplementation

def implementItem(prototype : ItemPrototype)(using I : ItemImplementation) : Item = I.implementItem(prototype)