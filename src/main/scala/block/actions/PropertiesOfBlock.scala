package me.zahara.fmc
package block.actions

import block.{Block, Properties}

trait PropertiesOfBlock[F[_]]:
  extension (block  :Block)
    def defaultProperties: F[Properties]
  end extension
end PropertiesOfBlock
