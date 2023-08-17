package fmc
package block.state

import block.Block
import syntax.show.{*, given}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BlockStateTest extends AnyFlatSpec with should.Matchers:

  private val testBoolProp = setProperty("isok", Set(true, false))
  private val testIntProp = setProperty("isok", Set(1, 2, 3, 4))
  private val testProperties = noProperties ++ (testBoolProp, true) ++ (testIntProp, 1)
  private val testBlock = Block(ResourceLocation("test", "test_block"), testProperties)

  "defaultStateOf" should "have the same properties as default in block" in {
    defaultStateOf(testBlock).properties should be(testProperties)
  }
  
  