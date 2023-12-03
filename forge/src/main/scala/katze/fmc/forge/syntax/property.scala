package katze.fmc.forge.syntax

import katze.fmc.block.state.Property as FmcProperty
import katze.fmc.collection.BiMap
import katze.fmc.forge.syntax.property.OrdWrapper
import net.minecraft.world.level.block.state.properties.Property as VanilaProperty

import java.util
import java.util.Optional
import scala.jdk.CollectionConverters.{ *, given }
import scala.math.Ordering.ordered
import scala.reflect.ClassTag

type P[T] = T match {
  case Comparable[T] => VanilaProperty[T]
  case _ => VanilaProperty[OrdWrapper[T]]
}

type PP[T] = T match {
  case OrdWrapper[u] => u
  case _ => T
}

object property:
  case class OrdWrapper[T](val value : T) extends Comparable[OrdWrapper[T]]:
    override def compareTo(o: OrdWrapper[T]): Int = ???
    
  def asVanilaProperty[T](propertyIn: FmcProperty[T]) : P[T] =
    propertyIn.values match
      case mapp: PropertyBiMap[u] => ??? // mapp.property.asInstanceOf[P[T]]
      case _ =>
        if propertyIn.clazz.runtimeClass.isAssignableFrom(classOf[Comparable[T]]) then
          AsVanilaProperty(propertyIn.asInstanceOf).asInstanceOf
        else
          ???
        end if
    end match
  end asVanilaProperty
  
  def asFmcProperty[T <: Comparable[T]](property: VanilaProperty[T]): FmcProperty[PP[T]] =
    property match
      case property: AsVanilaProperty[T] => property.property
      case property: OrdProperty[u] => property.property
      case property: VanilaProperty[T] => FmcProperty(property.getName, PropertyBiMap[T](property))(using implicitly, ClassTag(property.getValueClass))
    end match
  end asFmcProperty
  
  private class OrdProperty[T](val name : String, val property : FmcProperty[T]) extends VanilaProperty[OrdWrapper[T]](name, classOf):
    override val getPossibleValues: util.Collection[OrdWrapper[T]] = property.values.map(_._1).map(OrdWrapper[T]).asJavaCollection
    
    override def getValue(name : String): Optional[OrdWrapper[T]] =
      property.values.keyOf(name) match
        case Some(value) => Optional.of(OrdWrapper(value))
        case None => Optional.empty()
      end match
    end getValue
    
    override def getName(p_61696_ : OrdWrapper[T]): String =
      property.values.valueOf(p_61696_.value).get
    end getName
  end OrdProperty
  
  private class AsVanilaProperty[T <: Comparable[T]](val property: FmcProperty[T]) extends VanilaProperty[T](property.name, property.clazz.runtimeClass.asInstanceOf):
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
