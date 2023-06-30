package me.zahara.fmc
package block

import io.github.iltotore.iron.:|
import me.zahara.fmc.BlockPos
import me.zahara.fmc.block.BlockSettings
import syntax.all.{*, given}

import cats.*
import cats.data.OptionT

final case class BlockPrototype[F[_], Level, Block](
  name : String :| ResourcePath,
  settings: BlockSettings,
  properties : Properties,
  neighborUpdatedReaction : (self : Block, level : Level, pos : BlockPos, from : BlockPos) => F[Unit],
  stateForPlacement : (self : Block, level : Level, pos : BlockPos) => F[Properties]
)

