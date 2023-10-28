package katze.fmc.forge.syntax

import katze.fmc.block.state.Property as FmcProperty
import katze.fmc.collection.{ BiMap, ListBiMap }
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

import java.util
import java.util.Optional
import scala.jdk.CollectionConverters.{ *, given }

object property:
  def asVanilaProperty[T <: Comparable[T]](property: FmcProperty[T]) : VanilaProperty[T] =
    property.values match
      case value: PropertyBiMap[T] => value.property
      case _ => AsVanilaProperty[T](property)
    end match
  end asVanilaProperty
  
  def asFmcProperty[T <: Comparable[T]](property: VanilaProperty[T]): FmcProperty[T] =
    property match
      case property: AsVanilaProperty[T] => property.property
      case property: VanilaProperty[T] => FmcProperty(property.getName, PropertyBiMap[T](property))(using property.getValueClass)
    end match
  end asFmcProperty
  
  private class AsVanilaProperty[T <: Comparable[T]](val property: FmcProperty[T]) extends VanilaProperty[T](property.name, property.clazz):
    override val getPossibleValues: util.Collection[T] = property.values.map(_._1).asJavaCollection
    
    override def getName(value : T): String =
      property.values.valueOf(value).get // TODO Сделать ошибку о некорректном ключе получше, чем просто падение от пустого Option
    end getName
    
    
    override def getValue(key : String): Optional[T] =
      property.values.keyOf(key) match
        case Some(value) => Optional.of[T](value)
        case None => Optional.empty()
    end getValue
  end AsVanilaProperty
  
  private class PropertyBiMap[T <: Comparable[T]](val property: VanilaProperty[T]) extends BiMap[T, String]:
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

end property
