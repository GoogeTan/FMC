package me.zahara.fmc
package block

import block.BlockSettings
import block.action.PropertiesOfBlock
import block.material.Material
import syntax.all.{*, given}

import cats.*
import cats.data.OptionT
import io.github.iltotore.iron.:|
import me.zahara.fmc.level.Level

final case class BlockPrototype[F[_]](
  settings: BlockSettings,
  defaultProperties : Properties,
  neighborUpdatedReaction : (self : Block, level : Level, pos : BlockPos, from : BlockPos) => F[Unit],
  stateForPlacement : (self : Block, level : Level, pos : BlockPos) => F[BlockState]
)
