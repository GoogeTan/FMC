package katze.fmc.forge.block

import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.forge.IO
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.{ BlockEntity, BlockEntityTicker, BlockEntityType }
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.{ BaseEntityBlock, Block, EntityBlock }

import javax.annotation.Nullable

trait BlockEntityProvider[State](
                                  entityType : BlockEntityType[?],
                                  blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                ) extends Block with EntityBlock:
  @Nullable 
  override def newBlockEntity(position: BlockPos, state: BlockState): BlockEntity =
    ???
  
  @Nullable 
  override def getTicker[T <: BlockEntity](level: Level, state: BlockState, entityType: BlockEntityType[T]): BlockEntityTicker[T] =
    if entityType == this.entityType then
      (level: Level, pos: BlockPos, state: BlockState, entity: T) => ???
    else 
      null  
