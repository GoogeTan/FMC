package katze.fmc.item.stack

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.Positive
import katze.fmc.item.Item

final case class Stack(
                        item : Item,
                        data : ItemData,
                        count : Int :| Positive,
                        damage : Int :| Positive
                      )

type ItemData = Any // TODO Добавить нбт и капабилити.