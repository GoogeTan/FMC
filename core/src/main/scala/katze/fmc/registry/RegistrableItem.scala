package katze.fmc.registry

import katze.fmc.ResourceLocation
import katze.fmc.item.{ Item, ItemPrototype }

trait RegistrableItem[F[_]]:
  def registerItem(location: ResourceLocation, prototype: ItemPrototype) : F[Item]
end RegistrableItem

def registerItem[F[_]](location: ResourceLocation, prototype: ItemPrototype)(using reg : RegistrableItem[F]): F[Item] =
  reg.registerItem(location, prototype)
end registerItem
