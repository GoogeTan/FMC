package katze.fmc.creative.tab

import katze.fmc.item.stack.Stack
import katze.fmc.text.Text

final case class CreativeModeTabPrototype(
                                           displayName: Text,
                                           canScroll: Boolean = true,
                                           showTitle: Boolean = true,
                                           iconItemStack: Stack
                                         )
