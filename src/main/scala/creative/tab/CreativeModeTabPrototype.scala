package me.zahara.fmc
package creative.tab

import text.Component

import me.zahara.fmc.item.stack.Stack

case class CreativeModeTabPrototype(
                                     displayName: Component,
                                     canScroll: Boolean = true,
                                     showTitle: Boolean = true,
                                     iconItemStack: Stack
                                   )
