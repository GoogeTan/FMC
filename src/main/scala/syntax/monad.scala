package fmc
package syntax

import data.{ Monad, Swap }
import typing.{ Id, Inner }
import fmc.data.traverse

object monad:
  extension[F[_], T] (value : F[T])(using monad : Monad[F])
    def flatMap[B](func : T => F[B]) : F[B] =
      monad.mmap(value)(func)
    end flatMap


    def map[B](func : T => B) : F[B] =
      monad.fmap(value)(func)
    end map
  end extension
  
  object all:
    export option.{*, given}
    export either.{*, given}
    export inner.{*, given}
    export identity.{ *, given }
    export list.{ *, given }
  end all
  
  object option:
    given monadOption: Monad[Option] with
      override def mmap[A, B](value: Option[A])(func: A => Option[B]): Option[B] =
        value.flatMap(func)
      end mmap
  
      override def pure[T](value: T): Option[T] = Some(value)
    end monadOption
  end option

  object either:
    given rightEither[Error] : Monad[Either[Error, _]] with
      override def pure[T](value: T): Either[Error, T] = Right(value)
  
      override def mmap[A, B](value: Either[Error, A])(func: A => Either[Error, B]): Either[Error, B] =
        value.flatMap(func)
      end mmap
    end rightEither
  end either
  
  object inner:
    
    given innerMonad[F[_] : Monad, G[_] : Monad : Swap] : Monad[Inner[F, G, _]] with
      override def mmap[A, B](value: Inner[F, G, A])(func: A => Inner[F, G, B]): Inner[F, G, B] =
        data.mmap[F, G[A], G[B]](value) { (innerValue : G[A]) =>
          data.fmap(
            innerValue.traverse[[T] =>> F[G[T]], B](func)
          )(data.mmap(_)(scala.Predef.identity))
        }
      end mmap
  
      override def pure[T](value: T): Inner[F, G, T] =
        data.pure(data.pure(value))
      end pure
    end innerMonad
  end inner
  
  object identity:
    given identityMonad : Monad[Id] with
      override def apply[A, B](value: A)(func: A => B): B =
        func(value)
      end apply
  
      override def mmap[A, B](value: A)(func: A => B): B =
        func(value)
      end mmap
  
      override def pure[T](value: T): T = value
    end identityMonad
  end identity
  
  object list:
    given monadList: Monad[List] with
      override def fmap[A, B](value: List[A])(func: A => B): List[B] =
        value.map(func)
      end fmap
  
      override def mmap[A, B](value: List[A])(func: A => List[B]): List[B] =
        value.flatMap(func)
      end mmap
  
      override def pure[T](value: T): List[T] = value :: Nil
    end monadList
  end list
end monad
