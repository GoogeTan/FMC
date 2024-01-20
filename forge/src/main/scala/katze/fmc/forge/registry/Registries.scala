package katze.fmc.forge.registry

import katze.fmc.ModId
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

import scala.collection.mutable

class Registries:
  private val allTheRegistries : mutable.Map[(ModId, ResourceKey[_ <: Registry[?]]), DeferredRegister[?]] = mutable.Map.empty
  
  def registryFor[T](modId : ModId, registry : ResourceKey[Registry[T]]) : DeferredRegister[T] =
    val result : DeferredRegister[?] = allTheRegistries.getOrElseUpdate((modId, registry), DeferredRegister.create[T](registry, modId.toString))
    result.asInstanceOf[DeferredRegister[T]]
  end registryFor
  
  def subscribeForBus(bus : IEventBus) : Unit =
    allTheRegistries.values.foreach(_.register(bus))
  end subscribeForBus
end Registries
