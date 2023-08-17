package fmc
package block.state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class PropertiesTest extends AnyFlatSpec with should.Matchers:
  "withValueFor" should "add all the valid values" in {
    val testProperty = intProperty("abacaba", 1, 5)
    val zeroProperty = noProperties
    for value <- 1 to 5 do
      val withValue = withValueFor(zeroProperty, testProperty, value)
      val settedValue = valueOf(withValue, testProperty)
      settedValue should be (Some(value))
    end for
  }

  "withValueFor" should "skip all the invalid values" in {
    val testProperty = intProperty("abacaba", 1, 5)
    val zeroProperty = noProperties
    for value <- List(-1, 6, 100000, -99999) do
      val withValue = withValueFor(zeroProperty, testProperty, value)
      val settedValue = valueOf(withValue, testProperty)
      settedValue.isEmpty should be (true)
    end for
  }

end PropertiesTest
