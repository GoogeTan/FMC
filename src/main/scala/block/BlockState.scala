package me.zahara.fmc
package block

import block.actions.PropertiesOfBlock
import collection.ListBiMap
import syntax.all.{*, given}

import cats.Functor

case class BlockState private (block : Block, properties : Properties):
  /**
   * Обновляет состояние проперти
   * @return Some, если у блока есть данная проперти, иначе None
   */
  def withValue[T](property: Property[T], value : T) : Option[BlockState] =
    if properties.valueOf(property).isDefined then
      Some(BlockState(block, properties.withValue(property, value)))
    else
      None
    end if
  end withValue
end BlockState

object BlockState:
  def defaultFor[F[_] : Functor : PropertiesOfBlock](block: Block) : F[BlockState] =
    block.defaultProperties.map(BlockState(block, _))
  end defaultFor
end BlockState
