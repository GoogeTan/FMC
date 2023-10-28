package katze.fmc.syntax

import katze.fmc.data.NBT.{Compound, End}
import katze.fmc.data.Serializable
import katze.fmc.data.NBT

import scala.::

object serializable:
  given Serializable[Byte] with
    override def deserialize(container: NBT): Option[Byte] =
      container match
        case NBT.ByteValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Byte): NBT =
      NBT.ByteValue(value)
    end serialize
  end given

  given Serializable[Short] with
    override def deserialize(container: NBT): Option[Short] =
      container match
        case NBT.ShortValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Short): NBT =
      NBT.ShortValue(value)
    end serialize
  end given

  given Serializable[Int] with
    override def deserialize(container: NBT): Option[Int] =
      container match
        case NBT.IntValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Int): NBT =
      NBT.IntValue(value)
    end serialize
  end given


  given Serializable[Long] with
    override def deserialize(container: NBT): Option[Long] =
      container match
        case NBT.LongValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Long): NBT =
      NBT.LongValue(value)
    end serialize
  end given

  given Serializable[Float] with
    override def deserialize(container: NBT): Option[Float] =
      container match
        case NBT.FloatValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Float): NBT =
      NBT.FloatValue(value)
    end serialize
  end given

  given Serializable[Double] with
    override def deserialize(container: NBT): Option[Double] =
      container match
        case NBT.DoubleValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Double): NBT =
      NBT.DoubleValue(value)
    end serialize
  end given

  given Serializable[String] with
    override def deserialize(container: NBT): Option[String] =
      container match
        case NBT.StringValue(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: String): NBT =
      NBT.StringValue(value)
    end serialize
  end given

  given byteArraySerialize: Serializable[Array[Byte]] with
    override def deserialize(container: NBT): Option[Array[Byte]] =
      container match
        case NBT.ByteArray(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Array[Byte]): NBT =
      NBT.ByteArray(value)
    end serialize
  end byteArraySerialize

  given intArraySerialize: Serializable[Array[Int]] with
    override def deserialize(container: NBT): Option[Array[Int]] =
      container match
        case NBT.IntArray(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Array[Int]): NBT =
      NBT.IntArray(value)
    end serialize
  end intArraySerialize

  given longArraySerialize: Serializable[Array[Long]] with
    override def deserialize(container: NBT): Option[Array[Long]] =
      container match
        case NBT.LongArray(value) => Some(value)
        case _ => None
      end match
    end deserialize

    override def serialize(value: Array[Long]): NBT =
      NBT.LongArray(value)
    end serialize
  end longArraySerialize


  given tArraySerialize[T](using ser : Serializable[T]): Serializable[List[T]] with
    override def deserialize(container: NBT): Option[List[T]] =
      container match
        case tag :  NBT.Compound =>
          for
            length <- katze.fmc.data.get[Int]("size", tag)
            values <- (0 to length).map(_.toString).map(katze.fmc.data.get[T](_, tag)).foldRight(Option(List[T]())) {
              case (Some(value), Some(res)) => Some(::.apply(value, res))
              case _ => None
            }
          yield values
        case _ => None
    end deserialize

    override def serialize(value: List[T]): NBT =
      val values = mapIndexed(value)((index, value) => (index.toString, ser.serialize(value)))

      NBT.Compound((("index" -> NBT.IntValue(value.length)) :: values).toMap)
    end serialize

    def mapIndexed[A, B](lst : List[A], baseIndex : Int = 0)(func : (Int, A) => B) : List[B] =
      lst match
        case ::(head, next) => func(baseIndex, head) :: mapIndexed(next, baseIndex + 1)(func)
        case Nil => Nil
      end match
    end mapIndexed
  end tArraySerialize
end serializable
