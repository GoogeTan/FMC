package me.zahara.fmc
package item

import text.Component

case class CreativeModeTab(
                       id: Int = 0,
                       displayName: Component,
                       canScroll: Boolean = true,
                       showTitle: Boolean = true,
                       iconItemStack: Any , // TODO
                     )