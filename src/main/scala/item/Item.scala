package me.zahara.fmc
package item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.{GreaterEqual, Positive}
import io.github.iltotore.iron.constraint.numeric.Greater

final case class Item(location: ResourceLocation, maxStackSize : Int :| Positive, maxDamage : Int :| GreaterEqual[0])