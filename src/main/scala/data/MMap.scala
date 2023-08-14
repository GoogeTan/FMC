package fmc.data

trait MMap[F[_]]:
  def mmap[A, B](value : F[A])(func : A => F[B]) : F[B]
end MMap

def mmap[F[_], A, B](value : F[A])(func : A => F[B])(using mmp : MMap[F]) : F[B] =
  mmp.mmap(value)(func)
end mmap
