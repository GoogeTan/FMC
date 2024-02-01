package katze.fmc.forge.registry

import katze.fmc.block.BlockPrototype
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.forge.IO
import katze.fmc.forge.block.BlockEntityProvider
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.{ Block, EntityBlock }
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour

trait BlockWithEntityImplementation[-F[_], Level, +ClientLevel <: Level, +ServerLevel <: Level]:
  def implementBlockWithEntity[State](
                        typeIn: BlockEntityType[?],
                        prototype: BlockPrototype[IO, Level],
                        blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                      ): Block with EntityBlock
end BlockWithEntityImplementation

object BlockWithEntityImplementation extends BlockWithEntityImplementation[IO, Level, ClientLevel, ServerLevel]:
  override def implementBlockWithEntity[State](
                                                typeIn: BlockEntityType[_], 
                                                prototype: BlockPrototype[IO, Level], 
                                                blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                              ): Block with EntityBlock =
    new Block(BlockBehaviour.Properties.of()) with BlockEntityProvider[State](typeIn, blockEntityPrototype)

def implementBlockWithEntity[F[_], Level, ClientLevel <: Level, ServerLevel <: Level, State](
                                                                              typeIn: BlockEntityType[?],
                                                                              prototype: BlockPrototype[IO, Level],
                                                                              blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                                                            )(
                                                                              using I : BlockWithEntityImplementation[F, Level, ClientLevel, ServerLevel]
                                                                            ): Block with EntityBlock = I.implementBlockWithEntity(typeIn, prototype, blockEntityPrototype)