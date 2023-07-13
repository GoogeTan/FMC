package me.zahara.fmc
package registry

import item.{ItemPrototype, Item}

trait RegistrableItem[F[_]]:
  def registerItem(location: ResourceLocation, prototype: ItemPrototype) : F[Item]
end RegistrableItem
