package katze.fmc.forge

import katze.fmc.example.ExampleMod
import katze.fmc.forge.registry.{ BlockImplementation, BlockRegistry, BlockWithEntityImplementation, BlockWithEntityRegistry, Registries as FmcRegistries }
import katze.fmc.forge.subscribeable.{ ModSubscribeable, Subscribeable }
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.registry.{ RegistrableBlock, RegistrableBlockEntity }
import katze.fmc.syntax.all.{ *, given }
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType

object Test:
  private val regs = FmcRegistries()
  
  private val registrableBlockEntity : RegistrableBlockEntity[IO, Level, ClientLevel, ServerLevel] =
    BlockWithEntityRegistry(
      regs.registryFor[Block](_, Registries.BLOCK),
      regs.registryFor[BlockEntityType[?]](_, Registries.BLOCK_ENTITY_TYPE)
    )(using BlockWithEntityImplementation)
    
  private val registrableBlock : RegistrableBlock[IO, Level] =
    BlockRegistry(regs.registryFor[Block](_, Registries.BLOCK))(using BlockImplementation)
    
  val subscribeable: Subscribeable = ModSubscribeable(
    ExampleMod[IO, Level, ClientLevel, ServerLevel]("test")(using implicitly, registrableBlock, registrableBlockEntity)
  )
  
