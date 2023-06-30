package me.zahara.fmc
package block.material


case class Material(
                      pushReaction: PushReaction = PushReaction.NORMAL,
                      blocksMotion: Boolean = true,
                      flammable: Boolean = false,
                      liquid: Boolean = false,
                      replaceable: Boolean = false,
                      solid: Boolean = true,
                      color: Option[Color] = None,
                      solidBlocking: Boolean = true,
                  )

