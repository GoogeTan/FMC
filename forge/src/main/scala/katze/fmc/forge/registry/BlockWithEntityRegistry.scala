package katze.fmc.forge.registry

import katze.fmc.block.{ BlockPrototype, BlockRegistryEntry }
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.{ ModId, ResourceLocation, data }
import katze.fmc.forge.IO
import katze.fmc.registry.RegistrableBlockEntity
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.{ Block, EntityBlock }
import net.minecraft.world.level.block.entity.{ BlockEntity, BlockEntityType }
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.DeferredRegister

class BlockWithEntityRegistry(
                                val blockRegister: ModId => DeferredRegister[Block],
                                val blockEntityTypeRegistry : ModId => DeferredRegister[BlockEntityType[?]]
                              )(
                                using BlockWithEntityImplementation[IO, Level, ClientLevel, ServerLevel]
                              ) extends RegistrableBlockEntity[IO, Level, ClientLevel, ServerLevel]:
  override def registerBlockWithEntity[State: data.Serializable](
                                                                  resourceLocation: ResourceLocation, 
                                                                  prototype: BlockPrototype[IO, Level], 
                                                                  blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                                                ): IO[BlockRegistryEntry] =
    IO:
      lazy val block: Block with EntityBlock = implementBlockWithEntity(blockEntity, prototype, blockEntityPrototype)
      lazy val blockEntity = BlockEntityType.Builder.of[BlockEntity]((pos: BlockPos, state: BlockState) => block.newBlockEntity(pos, state), block).build(null)
      
      blockRegister(resourceLocation.namespace).register(resourceLocation.path, () => block)
      blockEntityTypeRegistry(resourceLocation.namespace).register(resourceLocation.path, () => blockEntity)
      
      BlockRegistryEntry(resourceLocation, prototype.defaultProperties)
  end registerBlockWithEntity
end BlockWithEntityRegistry
