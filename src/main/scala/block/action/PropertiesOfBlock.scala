package me.zahara.fmc
package block.action

import block.{Block, Properties}

trait PropertiesOfBlock[F[_]]:
  extension (block  :Block)
    def defaultProperties: F[Properties]
  end extension
end PropertiesOfBlock
