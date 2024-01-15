package katze.fmc.data

import katze.fmc.data.*

trait Monad[F[_]] extends FMap[F] with MMap[F] with Ap[F]:
  override def apply[A, B](value: F[A])(func: F[A => B]): F[B] =
    mmap(func)(f => fmap(value)(f))
  end apply
end Monad

extension[F[_], T] (value : F[T])(using monad : Monad[F])
  def flatMap[B](func : T => F[B]) : F[B] =
    monad.mmap(value)(func)
  end flatMap
end extension

extension[F[_] : Monad](value : F[Boolean])
  def ||(another : F[Boolean]) : F[Boolean] =
    value >>= (x => if (x) pure(true) else another)
  end ||
  
  def &&(another: F[Boolean]): F[Boolean] =
    value >>= (x => if (!x) pure(false) else another)
  end &&
  
  def &&(another: Boolean): F[Boolean] =
    value >>* (x => x && another)
  end &&
  
  def &&(another: java.lang.Boolean): F[Boolean] =
    value >>* (x => x && another)
  end &&
  
  def unary_! : F[Boolean] =
    fmap(value)(a => !a)
  end unary_!
end extension

extension(value : Boolean)
  def ||[F[_] : Monad](another : F[Boolean]) : F[Boolean] =
    if value then
      pure(true)
    else
      another
  end ||
  
  def &&[F[_] : Monad](another: F[Boolean]): F[Boolean] =
    if !value then
      pure(false)
    else
      another
  end &&
end extension

object Monad:
  def apply[F[_]](using m : Monad[F]): Monad[F] = m
end Monad
