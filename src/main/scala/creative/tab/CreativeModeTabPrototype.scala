package fmc
package creative.tab

import item.stack.Stack
import text.Component

final case class CreativeModeTabPrototype(
                                     displayName: Component,
                                     canScroll: Boolean = true,
                                     showTitle: Boolean = true,
                                     iconItemStack: Stack
                                   )
