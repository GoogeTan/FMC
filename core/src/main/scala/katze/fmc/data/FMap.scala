package katze.fmc.data

import scala.annotation.targetName

trait FMap[F[_]]:
  def fmap[A, B](value : F[A])(func : A => B) : F[B]
end FMap

def fmap[F[_], A, B](value : F[A])(func : A => B)(using fmp : FMap[F]) : F[B] =
  fmp.fmap(value)(func)
end fmap

def fmapi[F[_] : FMap, G[_] : FMap, A, B](value : F[G[A]])(func : A => B) : F[G[B]] =
  fmap(value)(fmap(_)(func))
end fmapi

extension [F[_] : FMap, A](value : F[A])
  /**
   * Refers to fmap
   */
  @targetName("fmap_operator")
  def >>*[B](func : A => B) : F[B] =
    fmap(value)(func)
  end >>*
  
  /**
   * Drops old result but keeps the effect
   */
  def *>[T](result : T) : F[T] =
    fmap(value)(_ => result)
  end *>
end extension


