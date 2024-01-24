package katze.fmc.forge.subscribeable

import net.minecraftforge.eventbus.api.IEventBus

class SubscribeableList(val subs : List[Subscribeable]) extends Subscribeable:
  override def subscribeEvent(bus: IEventBus): Unit =
    subs.foreach(_.subscribeEvent(bus))
  
  override def subscribeInit(bus: IEventBus): Unit =
    subs.foreach(_.subscribeInit(bus))
end SubscribeableList

    