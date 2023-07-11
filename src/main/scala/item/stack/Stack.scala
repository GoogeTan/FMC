package me.zahara.fmc
package item.stack

import item.Item

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.numeric.Interval.{ClosedOpen, OpenClosed}

case class Stack(
                  item : Item,
                  data : ItemData
                )(
                  count : Int :| OpenClosed[0, item.maxStackSize.type],
                  damage : Int :| OpenClosed[0, item.maxDamage.type]
                )

type ItemData = Any