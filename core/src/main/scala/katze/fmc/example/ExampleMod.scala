package katze.fmc.example

import katze.fmc.block.BlockSettings
import katze.fmc.{ Direction, Mod, ModId, ResourceLocation }
import katze.fmc.data.*
import katze.fmc.registry.*
import katze.fmc.syntax.all.{ *, given }
import katze.fmc.block.*
import katze.fmc.block.material.Material
import katze.fmc.block.state.*

class ExampleMod[
  F[_] : Monad, Level,
  ClientLevel <: Level,
  ServerLevel <: Level](
                          override val modId: ModId
                        )(using
                          RegistrableBlockEntity[F, Level, ClientLevel, ServerLevel],
                          RegistrableBlock[F, Level]
                        ) extends Mod[F]:
  private val blocks : List[(ResourceLocation, BlockPrototype[F, Level])] = List(
      ResourceLocation(modId, "piston") -> pistonBaseBlock(
        BlockSettings(Material()),
        setProperty("extended", Set(true, false)),
        setProperty("direction", Direction.allValues.toSet)
      )
  )
  
  private val registerBlocks : F[Unit] =
    blocks.traverse(registerBlock) *> ()
  end registerBlocks
  
  override val initCommon: F[Unit] =
    for
      _ <- registerBlocks
    yield ()  
  end initCommon
  
  override val initClient: F[Unit] = pure(())
  override val initServer: F[Unit] = pure(())
end ExampleMod
