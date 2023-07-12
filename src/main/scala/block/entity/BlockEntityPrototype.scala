package me.zahara.fmc
package block.entity

import block.Properties
import data.*
import item.stack.Stack

case class BlockEntityPrototype[
  F[_],
  State,
  Level,
  ClientLevel <: Level,
  ServerLevel <: Level
](
    createState : (pos : BlockPos, blockState : Properties) => Option[State],
    serverTick  : (level : ServerLevel, pos : BlockPos, state : State) => F[State],
    clientTick  : (level : ClientLevel, pos : BlockPos, state : State) => F[State],
    writeSelfToStack : (Stack, State) => Option[Stack]
)
