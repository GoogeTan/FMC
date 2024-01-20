package katze.fmc.forge.syntax

import _root_.io.github.iltotore.iron.refine
import katze.fmc.forge.syntax.resource.given
import katze.fmc.forge.syntax.stack.given
import katze.fmc.item as fitem
import net.minecraft.world.item as vitem
import net.minecraftforge.registries.ForgeRegistries

object item:
  given asVanila: Conversion[fitem.Item, vitem.Item] = item => ForgeRegistries.ITEMS.getValue(item.location)
  given asFmc: Conversion[vitem.Item, fitem.Item] =
    item =>
      fitem.Item(ForgeRegistries.ITEMS.getKey(item), (stack) => item.getMaxStackSize(stack).refine, (stack) => item.getMaxDamage(stack).refine) // TODO добавить номральный ошибки на случай не выполнения ограничений
end item

