package support


import cats.Monad
import cats.effect.IO

class IOMonad extends Monad[IO] {
  def pure[A](a: A): IO[A] = IO{a}
  def flatMap[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = fa flatMap f

  def tailRecM[A, B](a: A)(f: A => IO[Either[A, B]]): IO[B] = ???
}