package me.zahara.fmc

import item.ItemPrototype

import io.github.iltotore.iron.:|
import me.zahara.fmc.block.BlockPrototype

type ToRegisterList[T] = List[(ResourceLocation, T)]

case class Mod[F[_], Level](
                             name : String :| ResourceNamespace,
                             items : F[ToRegisterList[ItemPrototype]],
                             blocks : F[ToRegisterList[BlockPrototype[F, Level]]]
                           )
