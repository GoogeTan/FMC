package fmc
package block.material

import client.Color

final case class Material(
                              pushReaction: PushReaction = PushReaction.Normal,
                              blocksMotion: Boolean = true,
                              flammable: Boolean = false,
                              liquid: Boolean = false,
                              replaceable: Boolean = false,
                              solid: Boolean = true,
                              color: Option[Color] = None,
                              solidBlocking: Boolean = true,
                         )

