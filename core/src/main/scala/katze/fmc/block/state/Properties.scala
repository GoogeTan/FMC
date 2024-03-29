package katze.fmc.block.state

import scala.annotation.targetName

final case class Properties private[fmc] (private[block] val values : Map[Property[?], Any]):
  /**
   * Синоним withValueFor
   */
  @targetName("withValueForOperator")
  def ++[T](keyValuePair: (Property[T], T)): Properties =
    withValueFor(this, keyValuePair._1, keyValuePair._2)
  end ++
  
  /**
   * Синоним withValueFor
   */
  @targetName("leftWithValueForOperator")
  def ::[T](keyValuePair: (Property[T], T)): Properties =
    withValueFor(this, keyValuePair._1, keyValuePair._2)
  end ::
  
  def foldP[R](initial: R)(func: [T] => (R, Property[T], T) => R): R =
    values.iterator.foldLeft(initial)((res, propertyValue) => func(res, propertyValue._1, propertyValue._2.asInstanceOf))
  end foldP
  
  def foldPC[R](initial: R)(func: [T <: Comparable[T]] => (R, Property[T], T) => R): R =
    values.iterator.foldLeft(initial)((res, propertyValue) => func(res, propertyValue._1.asInstanceOf, propertyValue._2.asInstanceOf))
  end foldPC
  
  def mapP[R](func: [T <: Comparable[T]] => (Property[T], T) => R): List[R] =
    foldPC(List[R]())([T <: Comparable[T]] => (result: List[R], property: Property[T], value: T) => func(property, value) :: result)
  end mapP
end Properties

def noProperties : Properties = Properties(Map())

def foldP[R](properties: Properties, initial : R)(func : [T] => (R, Property[T], T) => R) : R =
  properties.values.iterator.foldLeft(initial)((res, propertyValue) => func(res, propertyValue._1, propertyValue._2.asInstanceOf))
end foldP

def foldPC[R](properties: Properties, initial: R)(func: [T <: Comparable[T]] => (R, Property[T], T) => R): R =
  properties.values.iterator.foldLeft(initial)((res, propertyValue) => func(res, propertyValue._1.asInstanceOf, propertyValue._2.asInstanceOf))
end foldPC

def mapP[R](properties: Properties)(func : [T <: Comparable[T]] => (Property[T], T) => R) : List[R] =
  foldPC(properties, List[R]())([T <: Comparable[T]]=> (result : List[R], property : Property[T], value : T) => func(property, value) :: result)
end mapP


def valueOf[T](properties: Properties, property: Property[T]) : Option[T] =
  properties.values.get(property).map(_.asInstanceOf[T])
end valueOf

/**
 * Вычисляет Properties с новым значением для данной проперти, если значение валидно. Отличается от безопасной версии тем, что опускает ошибку.
 */
def withValueFor[T](properties: Properties, property: Property[T], value: T) : Properties =
  withValueForSafe(properties, property, value).getOrElse(properties)
end withValueFor

/**
 * Вычисляет Properties с новым значением для данной проперти, если значение валидно
 */
def withValueForSafe[T](properties: Properties, property: Property[T], value: T) : Option[Properties] =
  if isValidFor(property, value) then
    Some(Properties(properties.values.updated(property, value)))
  else
    None
end withValueForSafe
