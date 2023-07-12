package me.zahara.fmc

import cats.Show

trait Log[F[_]]:
  def debug[T : Show](value : T) : F[Unit]

  def warn[T : Show](value : T) : F[Unit]

  def error[T : Show](value: T): F[Unit]
end Log
