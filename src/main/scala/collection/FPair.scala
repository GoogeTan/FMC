package me.zahara.fmc
package collection

case class FPair[F[_], T](key : F[T], value : T)
