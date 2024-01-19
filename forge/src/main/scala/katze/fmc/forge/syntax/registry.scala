package katze.fmc.forge.syntax

import katze.fmc.block.*
import katze.fmc.item.stack.*
import katze.fmc.block.entity.BlockEntityPrototype
import katze.fmc.forge.IO
import katze.fmc.forge.registry.*
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.forge.syntax.resource.asVanilaResourceLocation
import katze.fmc.item.{ Item, ItemPrototype }
import katze.fmc.registry.{ RegistrableBlock, RegistrableBlockEntity, RegistrableItem }
import katze.fmc.syntax.all.given
import katze.fmc.{ ResourceLocation, data }
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier
import net.minecraft.world.level.block.entity.{ BlockEntity, BlockEntityType }
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.{ Block, EntityBlock }
import net.minecraftforge.registries.ForgeRegistries

object registry:
  
  given (using BlockImplementation[IO, Level]): RegistrableBlock[IO, Level] = (location : ResourceLocation, prototype : BlockPrototype[IO, Level]) =>
    IO:
      ForgeRegistries.BLOCKS.register(
        asVanilaResourceLocation(location),
        implementBlock(prototype)
      )
      BlockRegistryEntry(location, prototype.defaultProperties)
  end given
  
  given(using BlockWithEntityImplementation[IO, Level, ClientLevel, ServerLevel]): RegistrableBlockEntity[IO, Level, ClientLevel, ServerLevel] with
    override def registerBlockWithEntity[State: data.Serializable](
                                                                    resourceLocation: ResourceLocation,
                                                                    prototype: BlockPrototype[IO, Level],
                                                                    blockEntityPrototype: BlockEntityPrototype[IO, Level, ClientLevel, ServerLevel, State]
                                                                  ): IO[BlockRegistryEntry] =
      IO:
        lazy val block : Block with EntityBlock = implementBlockWithEntity(blockEntity, prototype, blockEntityPrototype)
        lazy val blockEntity = BlockEntityType.Builder.of[BlockEntity]((pos: BlockPos, state: BlockState) => block.newBlockEntity(pos, state), block).build(null)
        
        ForgeRegistries.BLOCKS.register(resourceLocation, block)
        ForgeRegistries.BLOCK_ENTITY_TYPES.register(resourceLocation, blockEntity)
        
        BlockRegistryEntry(resourceLocation, prototype.defaultProperties)
    end registerBlockWithEntity
  end given
  
  given(using ItemImplementation, Conversion[Stack, ItemStack]): RegistrableItem[IO] with
    override def registerItem(location: ResourceLocation, prototype: ItemPrototype): IO[Item] =
      IO:
        val item = implementItem(prototype)
        ForgeRegistries.ITEMS.register(location, item)
        Item(location, (stack) => item.getMaxStackSize(stack), (stack) => item.getMaxDamage(stack))
    end registerItem
  end given
end registry
