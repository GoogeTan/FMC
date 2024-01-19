package katze.fmc.data

trait Throws[F[_], T]:
  def raise(value : T) : F[Nothing]
  
  def expect[N](maybe : Option[N], error : T) : F[N]
end Throws

