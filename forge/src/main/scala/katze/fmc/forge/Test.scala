package katze.fmc.forge

import katze.fmc.example.ExampleMod
import katze.fmc.forge.registry.{ BlockWithEntityRegistry, Registries as FmcRegistries }
import katze.fmc.forge.subscribeable.{ ModSubscribeable, Subscribeable }
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import katze.fmc.forge.syntax.all.{ *, given }
import katze.fmc.registry.RegistrableBlockEntity
import katze.fmc.syntax.all.{ *, given }
import net.minecraft.core.registries.Registries

object Test:
  private val regs = FmcRegistries()
  given RegistrableBlockEntity[IO, Level, ClientLevel, ServerLevel] =
    BlockWithEntityRegistry(regs.registryFor(_, Registries.BLOCK), regs.registryFor(_, Registries.BLOCK_ENTITY_TYPE))
    
  val subscribeable: Subscribeable = ModSubscribeable(
    ExampleMod[IO, Level, ClientLevel, ServerLevel]("test")
  )
  
