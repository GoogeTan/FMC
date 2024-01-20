package katze.fmc.forge.syntax

import katze.fmc.forge

object all:
  export katze.fmc.syntax.all.{*, given}
  export forge.syntax.block.{ *, given }
  export forge.syntax.blockpos.{ *, given }
  export forge.syntax.blockstate.{ *, given }
  export forge.syntax.direction.{ *, given }
  export forge.syntax.io.{ *, given }
  export forge.syntax.property.{ *, given }
  export forge.syntax.resource.{ *, given }
  export forge.syntax.stack.{*, given}
  export forge.syntax.item.{*, given}
end all

