package me.zahara.fmc

import level.{BlockPos, Properties}

trait Block[F[_], Level]:
  val name : String // TODO точный тип корректного названия
  val settings: BlockSettings
  val properties : Properties
  def stateForPlacement(level : Level, pos : BlockPos) : F[Properties]
  def neighborUpdated(level : Level, pos : BlockPos, from : BlockPos) : F[Unit]
end Block
