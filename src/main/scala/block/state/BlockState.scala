package fmc
package block.state

import block.Block
import block.state.*
import collection.*
import syntax.all.{*, given}


/**
 * Состояние блока в мире.
 * @param block блок, состояние которого описано.
 * @param properties значение всех проперти блока. Гарантировано, что установлены все проперти и имеют значения, а так же что лишние проперти не установлены.
 */
final case class BlockState private[state] (block : Block, properties : Properties)

/**
 * Создаёт стандартное состояние блока
 */
def defaultStateOf(block : Block) : BlockState =
  BlockState(block, block.defaultState)
end defaultStateOf

/**
 * Обновляет состояние проперти
 * @return Some, если у блока есть данная проперти, иначе None
 */
def withValue[T <: Comparable[T]](state : BlockState, property: Property[T], value: T) : Option[BlockState] =
  if block.state.valueOf(state.properties, property).isDefined then
    Some(BlockState(state.block, withValueFor(state.properties, property, value)))
  else
    None
  end if
end withValue

def withProperties(blockState : BlockState, properties: Properties) : Option[BlockState] =
  foldP(properties, Option(blockState))(
    [T <: Comparable[T]] =>
      (result: Option[BlockState], property: Property[T], value: T) =>
        result.flatMap(withValue(_, property, value))
  )
end withProperties

def valueFromState[T <: Comparable[T]](state: BlockState, property: Property[T]) : Option[T] =
  block.state.valueOf(state.properties, property)
end valueFromState

