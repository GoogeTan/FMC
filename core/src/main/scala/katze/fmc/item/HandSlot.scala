package katze.fmc.item

enum HandSlot(index : Int, filterFlag : Int, name : String):
  case MAINHAND extends HandSlot(0, 0, "mainhand")
  case OFFHAND extends HandSlot(1, 5, "offhand")
end HandSlot
