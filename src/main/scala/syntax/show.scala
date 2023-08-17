package fmc
package syntax

import fmc.block.state.*
import data.*
import collection.*
import fmc.block.Block
import syntax.bimap.{*, given}

object show:
  given Show[String] = a => a

  given Show[Number]  = _.toString

  given Show[Int] = _.toString
  
  given showList[T : Show] : Show[List[T]] =
    value => "[" + join(", ", value.map(data.show)) + "]"

  private def join(sep: String, data: List[String]): String =
    data match
      case head :: next :: tail => head + sep + join(sep, next :: tail)
      case head :: Nil => head
      case Nil => ""
    end match
  end join

  given showPair[A : Show, B : Show] : Show[(A, B)] = (a, b) => s"${data.show(a)} -> ${data.show(b)}"

  given shortBlockPos: Show[BlockPos] = value => s"{${value.x};${value.y};${value.z}}"
  given longBlockPos: Show[BlockPos] = value => s"Position(x:${value.x}, y:${value.y}, z:${value.z})"

  given scalaBoolShow : Show[Boolean] = _.toString

  given javaBoolShow : Show[java.lang.Boolean] = _.toString

  given shortShowBlock: Show[Block] = a => s"Block(${a.location})"

  given longShowBlock: Show[Block] = a => s"Block(name=${a.location};defaultProperties=${shortPropertiesShow.show(a.defaultProperties)})"

  given shortPropertiesShow: Show[Properties] = a =>
    "["
      + join(", ",
          foldP(a, List[String]())(
            [T <: Comparable[T]] =>
              (res : List[String], prop : Property[T], value : T) =>
                s"${prop.name} -> ${collection.valueOf(prop.values, value)}" :: res
          )
        )
      + "]"

  given shortPropertyShow[T <: Comparable[T]]: Show[Property[T]] = a => s"Property(${a.name})"

  given longPropertyShow[T <: Comparable[T]]: Show[Property[T]] = a => s"Property(name=${a.name}, values=${join(",", a.values.values.map(_._2))}))"
end show

