package me.zahara.fmc
package example

import block.actions.PropertiesOfBlock
import block.material.Material
import block.*
import syntax.all.{*, given}

import cats.Monad

def exampleBlock[Level, F[_] : Monad : PropertiesOfBlock] : BlockPrototype[F, Level] = defaultBlock.copy(
  settings = BlockSettings(Material(liquid = true))
)