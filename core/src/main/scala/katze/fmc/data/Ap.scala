package katze.fmc.data

import scala.annotation.targetName

trait Ap[F[_]] extends FMap[F]:
  def pure[T](value : T) : F[T]
  
  def apply[A, B](value : F[A])(func : F[A => B]) : F[B]
  
  override def fmap[A, B](value : F[A])(func : A => B) : F[B] =
    apply(value)(pure(func))
  end fmap
end Ap

def pure[F[_], T](value : T)(using p : Ap[F]) : F[T] = 
  p.pure(value)
end pure

def nothingToDo[F[_] : Ap] = pure(())

def apply[F[_], A, B](value : F[A])(func : F[A => B])(using p : Ap[F]) : F[B] =
  p.apply(value)(func)
end apply

def product[F[_] : Ap, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  apply(fb)(fmap(fa)(a => b => (a, b)))
end product

def map2[F[_] : Ap, A, B, Z](fa: F[A], fb: F[B])(f: A => B => Z): F[Z] =
  apply(fb)(fmap(fa)(f))
end map2

def map3[F[_] : Ap, A, B, C, Z](fa: F[A], fb: F[B], fc : F[C])(f: A => B => C => Z): F[Z] =
  apply(fc)(map2(fa, fb)(f))
end map3

def map4[F[_] : Ap, A, B, C, D, Z](fa: F[A], fb: F[B], fc : F[C], fd : F[D])(f: A => B => C => D => Z): F[Z] =
  apply(fd)(map3(fa, fb, fc)(f))
end map4

def map5[F[_] : Ap, A, B, C, D, E, Z](fa: F[A], fb: F[B], fc : F[C], fd : F[D], fe : F[E])(f: A => B => C => D => E => Z): F[Z] =
  apply(fe)(map4(fa, fb, fc, fd)(f))
end map5

def map6[F[_] : Ap, A, B, C, D, E, G, Z](fa: F[A], fb: F[B], fc : F[C], fd : F[D], fe : F[E], fg : F[G])(f: A => B => C => D => E => G => Z): F[Z] =
  apply(fg)(map5(fa, fb, fc, fd, fe)(f))
end map6

def map7[F[_] : Ap, A, B, C, D, E, G, H, Z](fa: F[A], fb: F[B], fc : F[C], fd : F[D], fe : F[E], fg : F[G], fh : F[H])(f: A => B => C => D => E => G => H => Z): F[Z] =
  apply(fh)(map6(fa, fb, fc, fd, fe, fg)(f))
end map7

extension[F[_], T] (value: F[T])(using monad: Ap[F])
  /**
   * Позволяет опустить результат вычисления, сохраняя весь эффект.
   *
   * @return
   */
  @inline
  @targetName("dropResultKeepEffectOperator")
  def *>>[B](fb: F[B]): F[B] =
    apply(value)(fmap(fb)(a => * => a))
  end *>>
end extension

extension[F[_]](value : F[Boolean])(using ap : Ap[F])
  def ||(another : F[Boolean]) : F[Boolean] =
    map2(value, another)(a => b => a || b)
  end ||
  
  def &&(another: F[Boolean]): F[Boolean] =
    map2(value, another)(a => b => a && b)
  end &&
  
  def unary_! : F[Boolean] =
    fmap(value)(a => !a)
  end unary_!
end extension
  
  