package katze.fmc.forge

import katze.fmc.forge.subscribeable.Subscribeable
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(Main.MODID)
object Main:
  inline val MODID = "fmc"
  
  val sub: Subscribeable = Test.subscribeable
  sub.subscribeEvent(MinecraftForge.EVENT_BUS)
  sub.subscribeInit(FMLJavaModLoadingContext.get.getModEventBus)
