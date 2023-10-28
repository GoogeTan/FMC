package katze.fmc.text

trait Translate[F[_]]:
  def translate(key : String) : F[Option[String]]
end Translate

def translate[F[_]](key : String)(using tr : Translate[F]) : F[Option[String]] =
  tr.translate(key)
end translate
