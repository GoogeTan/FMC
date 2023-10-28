package katze.fmc.item.stack

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.numeric.Interval.OpenClosed
import katze.fmc.item.Item
final case class Stack(
                  item : Item,
                  data : ItemData
                )(
                  count : Int :| OpenClosed[0, item.maxStackSize.type],
                  damage : Int :| OpenClosed[0, item.maxDamage.type]
                )

type ItemData = Any