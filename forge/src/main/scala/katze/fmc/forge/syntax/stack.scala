package katze.fmc.forge.syntax

import katze.fmc.item.stack.Stack
import net.minecraft.world.item.ItemStack
import item.given
import _root_.io.github.iltotore.iron.refine

object stack:
  given Conversion[Stack, ItemStack] = x => ItemStack(x.item.convert, x.count)
  given Conversion[ItemStack, Stack] = x => Stack(x.getItem.convert, null, x.getCount.refine, x.getDamageValue.refine)
end stack
