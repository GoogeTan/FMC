package katze.fmc.block.state

import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block
import collection.*


/**
 * Состояние блока в мире.
 * @param block блок, состояние которого описано.
 * @param properties значение всех проперти блока. Гарантировано, что установлены все проперти и имеют значения, а так же что лишние проперти не установлены.
 */
final case class BlockState private[state] (block : BlockRegistryEntry, properties : Properties)

/**
 * Создаёт стандартное состояние блока
 */
def defaultStateOf(block : BlockRegistryEntry) : BlockState =
  BlockState(block, block.defaultProperties)
end defaultStateOf

/**
 * Обновляет состояние проперти
 * @return Some, если у блока есть данная проперти, иначе None
 */
def withValue[T](state : BlockState, property: Property[T], value: T) : Option[BlockState] =
  if block.state.valueOf(state.properties, property).isDefined then
    Some(BlockState(state.block, withValueFor(state.properties, property, value)))
  else
    None
  end if
end withValue

def withProperties(blockState : BlockState, properties: Properties) : Option[BlockState] =
  foldP(properties, Option(blockState))(
    [T] =>
      (result: Option[BlockState], property: Property[T], value: T) =>
        result.flatMap(withValue(_, property, value))
  )
end withProperties

def valueFromState[T](state: BlockState, property: Property[T]) : Option[T] =
  block.state.valueOf(state.properties, property)
end valueFromState