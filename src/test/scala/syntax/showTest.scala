package fmc
package syntax

import syntax.show.{*, given}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class showTest extends AnyFlatSpec with should.Matchers:
  "List(1, 2, 3)" should "be shown as [1, 2, 3]" in {
    data.show(List(1, 2, 3)) should be ("[1, 2, 3]")
  }

  "List()" should "be shown as []" in {
    data.show(List[Int]()) should be("[]")
  }

  "(1, List('a', 'b', 'c')" should "be shown as 1 -> [a, b, c]" in {
    data.show((1, List("a", "b", "c"))) should be("1 -> [a, b, c]")
  }

  "BlockPos(1, 1, 1)" should "be shown as Position(x: 1, y: 1, z: 1) by longBlockPos" in {
    data.show(BlockPos(1, 1, 1))(using longBlockPos) should be("Position(x:1, y:1, z:1)")
  }

  "BlockPos(1, 1, 1)" should "be shown as {1;1;1} by shortBlockPos" in {
    data.show(BlockPos(1, 1, 1))(using shortBlockPos) should be("{1;1;1})")
  }

  // TODO написать тесты для всего остального
end showTest
