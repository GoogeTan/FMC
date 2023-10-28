package katze.fmc.item

import katze.fmc.creative.tab.CreativeModeTab

final case class ItemPrototype(
                                itemType : ItemType,
                                isFireResistant: Boolean = false,
                                craftingRemainingItem : Option[Item],
                                creativeTabs : List[CreativeModeTab] = List()
                              )
