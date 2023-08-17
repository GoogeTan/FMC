package fmc

enum Direction extends Comparable[Direction]:
  case East
  case West
  case North
  case South
  case Up
  case Down

  override def compareTo(o: Direction): Int =
    this.ordinal.compareTo(o.ordinal)
  end compareTo

  override def toString: String =
    super.toString.toLowerCase
  end toString
end Direction


