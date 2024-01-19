package katze.fmc.forge.syntax

import katze.fmc.block.state.{ CaseProperty, Property as FmcProperty }
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

import java.util
import java.util.Optional
import scala.jdk.CollectionConverters.{ *, given }

object property:
  val test: FmcProperty[Integer] = katze.fmc.block.state.intProperty("aba", 1, 10)
  val vanila : VanilaProperty[Integer] = asVanilaProperty[Integer](test)
  
  extension[T <: Comparable[T]] (value: FmcProperty[T])
    def asVanila: VanilaProperty[T] = asVanilaProperty(value)
  end extension
  
  extension[T <: Comparable[T]]  (value: VanilaProperty[T])
    def asFmc: FmcProperty[T] = asFmcProperty(value)
  end extension
  
  def asVanilaProperty[T <: Comparable[T]](propertyIn: FmcProperty[T]) : VanilaProperty[T] =
    propertyIn match
      case CaseProperty(_, map: PropertyBiMap[?]) => map.property
      case property : CaseProperty[T] => AsVanilaProperty(property)
    end match
  end asVanilaProperty
  
  def asFmcProperty[T <: Comparable[T]](property: VanilaProperty[T]) : FmcProperty[T] =
    property match
      case property: AsVanilaProperty[t] => property.property
      case property => CaseProperty[T](property.getName, PropertyBiMap[T](property))(using property.getValueClass)
    end match
  end asFmcProperty
  
  private class AsVanilaProperty[T <: Comparable[T]](val property: CaseProperty[T]) extends VanilaProperty[T](property.name, property.clazz):
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
end property
