package fmc
package block.state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class PropertyTest  extends AnyFlatSpec with should.Matchers:
  "isValidFor" should "be true for all the valid values" in {
    val testProperty = intProperty("abacaba", 1, 5)
    for value <- 1 to 5 do
      isValidFor(testProperty, value) should be (true)
    end for
  }

  "isValidFor" should "be false for all the invalid values" in {
    val testProperty = intProperty("abacaba", 1, 5)
    for value <- List(-1, 100, Int.MaxValue, Int.MinValue, 100500) do
      isValidFor(testProperty, value) should be(false)
    end for
  }
end PropertyTest
