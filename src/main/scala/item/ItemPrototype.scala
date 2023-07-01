package me.zahara.fmc
package item

case class ItemPrototype(
                          itemType : ItemType,
                          isFireResistant: Boolean = false,
                          craftingRemainingItem : Option[ResourceLocation] = None, // TODO придумать типизировыанные предметы
                          creativeTabs : List[CreativeModeTab] = List()
                        )
