package fmc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import io.github.iltotore.iron.{*, given}
class ResourceLocationTest extends AnyFlatSpec with should.Matchers:
  "aba" should "be valid ResourceNamespace" in {
    "aba".refineOption[ResourceNamespace].isDefined should be(true)
  }

  "baca" should "be valid ResourcePath" in {
    "baca".refineOption[ResourcePath].isDefined should be(true)
  }

  "baca-ings" should "be valid ResourcePath" in {
    "baca-ings".refineOption[ResourcePath].isDefined should be(true)
  }

  "baca-ings" should "be valid ResourceNamespace" in {
    "baca-ings".refineOption[ResourceNamespace].isDefined should be(true)
  }


  "baca/unga" should "be valid ResourcePath" in {
    "baca/unga".refineOption[ResourcePath].isDefined should be(true)
  }

  "baca/unga1" should "not be valid ResourcePath" in {
    "baca/unga1".refineOption[ResourcePath].isEmpty should be(true)
  }
end ResourceLocationTest
