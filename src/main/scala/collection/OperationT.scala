package me.zahara.fmc
package collection

import cats.*
import cats.syntax.all.given

def mapT[F[_] : Functor, G[_], K](container: F[FPair[G, ?]])(func: [T] => (G[T], T) => K): F[K] =
  container.map { pair => func(pair.key, pair.value) }
end mapT

def foldT[F[_] : Foldable, G[_], B](container: F[FPair[G, ?]], initial: B)(func: [T] => (B, G[T], T) => B): B =
  Foldable[F].foldLeft(container, initial) { (result, pair) =>
    func(result, pair.key, pair.value)
  }
end foldT