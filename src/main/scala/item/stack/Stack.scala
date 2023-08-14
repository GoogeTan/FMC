package fmc
package item.stack

import item.Item

import syntax.all.{*, given}

final case class Stack(
                  item : Item,
                  data : ItemData
                )(
                  count : Int :| OpenClosed[0, item.maxStackSize.type],
                  damage : Int :| OpenClosed[0, item.maxDamage.type]
                )

type ItemData = Any