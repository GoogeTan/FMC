package katze.fmc.data

import katze.fmc.data.NBT

import scala.reflect.ClassTag

enum NBT:
  case ByteValue(value: Byte)
  case ShortValue(value: Short)
  case IntValue(value : Int)
  case LongValue(value : Long)
  case FloatValue(value: Float)
  case DoubleValue(value: Double)
  case StringValue(value : String)

  case ByteArray(array : Array[Byte])
  case IntArray(array: Array[Int])
  case LongArray(array: Array[Long])
  // Должен быть конкретный тип данных. Обязательно. Иначе будут эксепшены.
  case List[T <: NBT : ClassTag](array: scala.List[T])
  case Compound(values : Map[String, NBT])

  case End
end NBT
