package katze.fmc.forge.registry

import katze.fmc.block.BlockPrototype
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.forge.IO
import net.minecraft.world.level.block.{ Block, EntityBlock }
import net.minecraft.world.level.block.entity.BlockEntityType

trait BlockWithEntityImplementation[-F[_], Level, +ClientLevel <: Level, +ServerLevel <: Level]:
  def implementBlockEithEntity[State](
                        typeIn: BlockEntityType[?],
                        prototype: BlockPrototype[IO, Level],
                        blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                      ): Block with EntityBlock
end BlockWithEntityImplementation

def implementBlockWithEntity[F[_], Level, ClientLevel <: Level, ServerLevel <: Level, State](
                                                                              typeIn: BlockEntityType[?],
                                                                              prototype: BlockPrototype[IO, Level],
                                                                              blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                                                            )(
                                                                              using I : BlockWithEntityImplementation[F, Level, ClientLevel, ServerLevel]
                                                                            ): Block with EntityBlock = I.implementBlockEithEntity(typeIn, prototype, blockEntityPrototype)