package fmc
package syntax

import data.Foldable

object foldable:
  given Foldable[Iterable] with
    override def foldLeft[T, K](container: Iterable[T], initialValue: K)(compose: (K, T) => K): K =
      container.foldLeft(initialValue)(compose)
    end foldLeft

    override def foldRight[T, K](container: Iterable[T], initialValue: K)(compose: (T, K) => K): K =
      container.foldRight(initialValue)(compose)
    end foldRight
  end given
  
  
end foldable
