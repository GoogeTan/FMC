package fmc
package syntax

import data.Show

object show:
  given Show[String] with
    override def show(value: String): String = value
  end given

  given showList[T : Show] : Show[List[T]] with
    override def show(value: List[T]): String =
      "[" + join(", ", value.map(fmc.data.show)) + "]"

    def join(sep : String, data : List[String]) : String =
      data match
        case head :: next :: tail => head + sep + join(sep, next :: tail)
        case head :: Nil => head
        case Nil => ""
      end match
    end join
  end showList
  
  given canonicalBlockPos: Show[BlockPos] with
    override def show(value: BlockPos): String = 
      s"Position(x:${value.x}, y:${value.y}, z:${value.z})"
    end show
  end canonicalBlockPos
end show

