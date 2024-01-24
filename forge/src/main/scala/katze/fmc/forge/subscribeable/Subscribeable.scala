package katze.fmc.forge.subscribeable

import net.minecraftforge.eventbus.api.IEventBus

trait Subscribeable:
  def subscribeEvent(bus : IEventBus) : Unit
  def subscribeInit(bus : IEventBus) : Unit
end Subscribeable
