package katze.fmc.syntax

import katze.fmc.data.*
import katze.fmc.{ BlockPos, Direction }
import katze.fmc.block.BlockRegistryEntry
import katze.fmc.block.state.{ Properties, Property, foldP }

/**
 * TODO refactor names
 */
object show:
  given Show[String] = a => a

  given Show[Number]  = _.toString
  
  given showList[T : Show] : Show[List[T]] =
    value => "[" + value.map(katze.fmc.data.show).mkString(",") + "]"

  given showPair[A : Show, B : Show] : Show[(A, B)] = (a, b) => s"${katze.fmc.data.show(a)} -> ${katze.fmc.data.show(b)}"

  given shortBlockPos: Show[BlockPos] = value => s"{${value.x};${value.y};${value.z}}"
  given longBlockPos: Show[BlockPos] = value => s"Position(x:${value.x}, y:${value.y}, z:${value.z})"

  given scalaBoolShow : Show[Boolean] = _.toString

  given javaBoolShow : Show[java.lang.Boolean] = _.toString
  
  given shortShowBlock: Show[BlockRegistryEntry] = a => s"Block(${a.location})"

  given longShowBlock: Show[BlockRegistryEntry] = a => s"Block(name=${a.location};defaultProperties=${shortPropertiesShow.show(a.defaultProperties)})"
  
  given shortPropertiesShow: Show[Properties] = a =>
    "[" + foldP(a, List[String]())(
            [T] =>
              (res : List[String], prop : Property[T], value : T) =>
                s"${prop.name} -> ${prop.values.valueOf(value)}" :: res
          ).mkString(", ") + "]"

  given shortPropertyShow[T]: Show[Property[T]] = a => s"Property(${a.name})"

  given longPropertyShow[T]: Show[Property[T]] = a => s"Property(name=${a.name}, values=${a.values.map(_._2).toList.mkString(", ")}))"
  
  given showDirection : Show[Direction] = _.toString.toLowerCase
end show

