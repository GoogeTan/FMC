package fmc
package item

import creative.tab.CreativeModeTab

case class ItemPrototype(
                          itemType : ItemType,
                          isFireResistant: Boolean = false,
                          craftingRemainingItem : Option[Item],
                          creativeTabs : List[CreativeModeTab] = List()
                        )
