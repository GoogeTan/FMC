package me.zahara.fmc
package creative.tab.action

import creative.tab.CreativeModeTab

/**
 * Все ванильные вкладки креатива. Оптион в связи с возможностью изменения наличия вкладок в будущем.
 */
trait VanilaTabs:
  val buildingBlocks: Option[CreativeModeTab]
  val coloredBlocks: Option[CreativeModeTab]
  val natural: Option[CreativeModeTab]
  val functional: Option[CreativeModeTab]
  val redstone: Option[CreativeModeTab] 
  val hotbar: Option[CreativeModeTab]
  val search: Option[CreativeModeTab]
  val tools: Option[CreativeModeTab] 
  val combat: Option[CreativeModeTab] 
  val foodAndDrinks: Option[CreativeModeTab] 
  val ingredients: Option[CreativeModeTab]
  val spawnEggs: Option[CreativeModeTab] 
  val operator: Option[CreativeModeTab]
  val inventory: Option[CreativeModeTab]
end VanilaTabs
