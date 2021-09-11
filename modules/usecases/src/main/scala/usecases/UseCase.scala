package usecases

import cats.Monad

trait UseCase[F[_], InputData, OutputData] {
  def execute(inputData: InputData)(implicit F: Monad[F]): F[OutputData]
}