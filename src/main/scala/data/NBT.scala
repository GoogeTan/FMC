package me.zahara.fmc
package data

enum NBT:
  case ByteValue(value: Byte)
  case ShortValue(value: Short)
  case IntValue(value : Int)
  case LongValue(value : Long)
  case FloatValue(value: Float)
  case StringValue(value : String)
  case DoubleValue(value: Double)

  case ByteArray(array : Array[Byte])
  case IntArray(array: Array[Int])
  case LongArray(array: Array[Long])
  // Должен быть конкретный тип данных. Обязательно. Иначе будут эксепшены.
  case List[T <: NBT](array: scala.List[T])
  case Compound(values : Map[String, NBT])

  case End
end NBT
