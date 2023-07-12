package me.zahara.fmc
package data

enum NBT:
  case IntValue(value : Int)
  case StringValue(value : String)
  case LongValue(value : Long)
  case FloatValue(value: Float)
  case Compound(values : Map[String, NBT])
end NBT
