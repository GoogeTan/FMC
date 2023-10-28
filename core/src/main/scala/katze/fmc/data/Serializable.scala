package katze.fmc.data

trait Serializable[T]:
  def serialize(value : T) : NBT

  def deserialize(container : NBT) : Option[T]
end Serializable

def get[T](name : String, tag : NBT.Compound)(using ser : Serializable[T]) : Option[T] =
  tag.values.get(name).flatMap(ser.deserialize)
end get

