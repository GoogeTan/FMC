package katze.fmc.item

import katze.fmc.ResourceLocation
import katze.fmc.item.stack.Stack
import katze.fmc.syntax.all.{ *, given }

final case class Item private[fmc](
                                    location: ResourceLocation, 
                                    maxStackSize : Stack => Int :| Positive, 
                                    maxDamage : Stack => Int :| GreaterEqual[0]
                                  )