package katze.fmc.block.entity

import katze.fmc.BlockPos
import katze.fmc.block.state.BlockState
import katze.fmc.item.stack.Stack

final case class BlockEntityPrototype[
  F[_],
  Level,
  ClientLevel <: Level,
  ServerLevel <: Level,
  State
](
   createState : (BlockPos, BlockState) => State,
   clientTick: BlockEntityTick[F, ClientLevel, State],
   serverTick  : BlockEntityTick[F, ServerLevel, State],
   writeSelfToStack : (Stack, State) => Option[Stack] = { (_ : Stack, _ : State) => None },
   sharedDefaults : List[SharedDefault[F, ServerLevel, ?]] = List()
)

@FunctionalInterface
trait BlockEntityTick[F[_], Level, State]:
  def tick(level : Level, pos : BlockPos, state : State) : F[State]
end BlockEntityTick
