package me.zahara.fmc
package collection

case class Sigma[F[_], T](key : F[T], value : T)
