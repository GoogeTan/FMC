package katze.fmc.forge.registry

import katze.fmc.{ ModId, ResourceLocation }
import katze.fmc.forge.IO
import katze.fmc.item.{ Item, ItemPrototype }
import katze.fmc.registry.RegistrableItem
import net.minecraftforge.registries.DeferredRegister
import net.minecraft.world.item
import katze.fmc.forge.syntax.all.{*, given}
import katze.fmc.syntax.all.{*, given}

class ItemRegistry(val registry : ModId => DeferredRegister[item.Item])(using ItemImplementation) extends RegistrableItem[IO]:
  override def registerItem(location: ResourceLocation, prototype: ItemPrototype): IO[Item] =
    IO:
      val it  = implementItem(prototype)
      registry(location.namespace).register(location.path, () => it)
      Item(location, (stack) => it.getMaxStackSize(stack).refine, (stack) => it.getMaxDamage(stack).refine) // TODO добавить номральный ошибки на случай не выполнения ограничений
  end registerItem
end ItemRegistry

