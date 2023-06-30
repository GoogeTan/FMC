package me.zahara.fmc
package block

trait BlockState:
  val block : ResourceLocation
  val properties : Properties

  /**
   * Обновляет состояние проперти
   * @return Some, если у блока есть данная проперти, иначе None
   */
  def withValue[T](property: Property[T], value : T) : Option[BlockState]
end BlockState
