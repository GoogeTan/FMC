package fmc
package collection

/**
 * Разделяет стираемый тип Т на составляющие его связанные подзначения F[U] и G[U]. 
 * @tparam T стираемый тип. Например FPair[F, ?]
 * @tparam F тип компонент 1, например Property
 * @tparam G тип компонент 2, зачастую Id
 */
trait Split[T, F[_], G[_]]:
  def apply[K](value : T, func : [U] => (F[U], G[U]) => K) : K
end Split

object Split:
  def apply[T, F[_], G[_]](using sp : Split[T, F, G]): Split[T, F, G] = sp