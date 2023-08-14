package fmc
package action

import data.Show

trait Log[F[_]]:
  def debug[T : Show](value : T) : F[Unit]

  def warn[T : Show](value : T) : F[Unit]

  def error[T : Show](value: T) : F[Unit]
end Log


def debug[F[_], T: Show](value: T)(using log : Log[F]): F[Unit] =
  log.debug(value)
end debug

def warn[F[_], T: Show](value: T)(using log : Log[F]): F[Unit]=
  log.warn(value)
end warn

def error[F[_], T: Show](value: T)(using log : Log[F]): F[Unit]=
  log.error(value)
end error
