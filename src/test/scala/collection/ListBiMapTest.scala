package fmc
package collection

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import syntax.bimap.{*, given}

class ListBiMapTest extends AnyFlatSpec with should.Matchers:
  "(1 -> 2), (2 -> 3)" should "be valid ListBiMap" in {
    ListBiMap(
      1 -> 2,
      2 -> 3
    ).isDefined should be(true)
  }

  "(1 -> 2), (2 -> 2)" should "not be valid ListBiMap" in {
    ListBiMap(
      1 -> 2,
      2 -> 2
    ) should be(None)
  }

  "(1 -> aba), (2 -> caba)" should "be valid ListBiMap" in {
    ListBiMap(
      1 -> "aba",
      2 -> "caba"
    ).isDefined should be(true)
  }

  "key 1" should "be present in ListBiMap(1 -> '123')" in {
    containsKey(ListBiMap(1 -> "123").get, 1) should be(true)
  }

  "value '123'" should "be present in ListBiMap(1 -> '123')" in {
    containsValue(ListBiMap(1 -> "123").get, "123") should be(true)
  }

  "key 1" should "have value '123' in ListBiMap(1 -> '123')" in {
    valueOf(ListBiMap(1 -> "123").get, 1) should be(Some("123"))
  }

  "value '123'" should "have key '1' in ListBiMap(1 -> '123')" in {
    keyOf(ListBiMap(1 -> "123").get, "123") should be(Some(1))
  }

  "key 2" should "not have value in ListBiMap(1 -> '123')" in {
    valueOf(ListBiMap(1 -> "123").get, 2) should be(None)
  }

  "value '1234'" should "not have key in ListBiMap(1 -> '123')" in {
    keyOf(ListBiMap(1 -> "123").get, "1234") should be(None)
  }
end ListBiMapTest
