package fmc.data

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

def apply[F[_], A, B](value : F[A])(func : F[A => B])(using p : Ap[F]) : F[B] =
  p.apply(value)(func)
end apply

def product[F[_] : Ap, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  apply(fb)(fmap(fa)(a => b => (a, b)))


def map2[F[_] : Ap, A, B, Z](fa: F[A], fb: F[B])(f: (A, B) => Z): F[Z] =
  fmap(product(fa, fb))(f.tupled)