package katze.fmc.forge.syntax

import katze.fmc.collection.BiMap
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

import scala.jdk.CollectionConverters.{ *, given }

class PropertyBiMap[T <: Comparable[T]](val property: VanilaProperty[T]) extends BiMap[T, String]:
  override def keyOf(value: String): Option[T] =
    val opt = property.getValue(value)
    if opt.isEmpty then
      None
    else
      Some(opt.get())
    end if
  end keyOf
  
  override def valueOf(key: T): Option[String] =
    if property.getPossibleValues.contains(key) then
      Some(property.getName(key))
    else
      None
    end if
  end valueOf
  
  private val values: Iterable[(T, String)] = property.getPossibleValues.asScala.map(value => (value, property.getName(value)))
  
  override def iterator: Iterator[(T, String)] = values.iterator
end PropertyBiMap
