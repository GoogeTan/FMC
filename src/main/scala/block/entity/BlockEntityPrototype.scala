package fmc
package block.entity

import block.state.{BlockState, Properties}
import data.*
import item.stack.Stack
import level.Level

final case class BlockEntityPrototype[
  F[_],
  State,
](
   createState : (pos : BlockPos, blockState : BlockState) => State,
   serverTick  : (level : Level.Server, pos : BlockPos, state : State) => F[State],
   clientTick  : (level : Level.Client, pos : BlockPos, state : State) => F[State],
   writeSelfToStack : (Stack, State) => Option[Stack] = { (_ : Stack, _ : State) => None },
   sharedDefaults : List[SharedDefault[F, ?]] = List()
)

