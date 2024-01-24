package katze.fmc.forge.subscribeable
import katze.fmc.Mod
import katze.fmc.forge.IO
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.event.lifecycle.*

class ModSubscribeable(mod : Mod[IO]) extends Subscribeable:
  override def subscribeEvent(bus: IEventBus): Unit =
    ()
  end subscribeEvent
  
  override def subscribeInit(bus: IEventBus): Unit =
    bus.addListener[FMLCommonSetupEvent]:
      event => mod.initCommon.runUnsafe
    bus.addListener[FMLClientSetupEvent]:
      event => mod.initClient.runUnsafe
    bus.addListener[FMLDedicatedServerSetupEvent]:
      event => mod.initServer.runUnsafe
  end subscribeInit
end ModSubscribeable

