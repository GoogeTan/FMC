package fmc
package collection

import action.*
import data.*
import syntax.all.{*, given}

/**
 *  Является более конкретной версией функции mapE, которая нужна из-за того, что вывод типов не позвоялет пользоватсья ей напрямую без конкретизации типов.
 */
def mapT[F[_] : FMap, G[_], K](container: F[FPair[G, ?]])(func: [T] => (G[T], T) => K) : F[K] =
  mapE[F, G, Id, FPair[G, ?], K](container)(func)
end mapT

/**
 *  Является более конкретной версией функции foldE, которая нужна из-за того, что вывод типов не позвоялет пользоватсья ей напрямую без конкретизации типов.
 */
def foldT[F[_] : Foldable, G[_], B](container: F[FPair[G, ?]], initial: B)(func: [T] => (B, G[T], T) => B) : B =
  foldE[F, G, Id, FPair[G, ?], B](container, initial)(func)
end foldT

/**
 * Позволяет построить отображение для любого стираемого типа в любом контейнере.
 */
def mapE[F[_] : FMap, G[_], H[_], K : Split[_, G, H], B](container: F[K])(func: [T] => (G[T], H[T]) => B) : F[B] =
  fmap(container) { pair =>
    Split[K, G, H].apply(pair, func)
  }
end mapE

def foldE[
  Container[_] : Foldable,
  F[_],
  G[_],
  T : Split[_, F, G],
  Acc
](
   container: Container[T],
   initial: Acc)(
   func: [Erased] => (Acc, F[Erased], G[Erased]) => Acc
) : Acc =
  foldLeft(container, initial) { (result, pair) =>
    Split[T, F, G].apply(pair, [Erased] => (g : F[Erased], t : G[Erased]) => func(result, g, t))
  }
end foldE
