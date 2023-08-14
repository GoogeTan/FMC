package fmc
package item

import syntax.all.{*, given}

final case class Item private[fmc](location: ResourceLocation, maxStackSize : Int :| Positive, maxDamage : Int :| GreaterEqual[0])