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
  def withValue[T <: Comparable[T]](property: Property[T], value : T) : Option[BlockState] =
    if properties.valueOf(property).isDefined then
      Some(BlockState(block, properties.withValueFor(property, value)))
    else
      None
    end if
  end withValue
end BlockState

object BlockState:
  def defaultFor[F[_] : Functor : PropertiesOfBlock](block: Block) : F[BlockState] =
    block.defaultProperties.map(BlockState(block, _ : Properties))
  end defaultFor

  def forWith[F[_] : Functor : PropertiesOfBlock](block : Block, properties: Properties) : F[Option[BlockState]] =
    defaultFor(block).map(withProperties(_, properties))
  end forWith

  def withProperties(blockState : BlockState, properties: Properties) : Option[BlockState] =
    properties.foldT[Option[BlockState]](Some(blockState))(
      [T <: Comparable[T]] =>
        (result: Option[BlockState], property: Property[T], value: T) =>
          result >>= { b => b.withValue(property, value) }
    )
  end withProperties
end BlockState
