package me.zahara.fmc
package text

trait Translate[F[_]]:
  def translate(key : String) : F[Option[String]]
end Translate
