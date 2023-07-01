package me.zahara.fmc
package item

enum ArmorSlot(index : Int, filterFlag : Int, name : String):
  case Feet  extends ArmorSlot(0, 1, "feet")
  case Legs  extends ArmorSlot(1, 2, "legs")
  case Chest extends ArmorSlot(2, 3, "chest")
  case Head  extends ArmorSlot(3, 4, "head")
end ArmorSlot
