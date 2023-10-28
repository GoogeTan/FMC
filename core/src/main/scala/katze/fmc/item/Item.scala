package katze.fmc.item

import katze.fmc.ResourceLocation
import katze.fmc.syntax.all.{*, given}

final case class Item private[fmc](location: ResourceLocation, maxStackSize : Int :| Positive, maxDamage : Int :| GreaterEqual[0])