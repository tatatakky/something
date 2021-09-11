package usecases.slack

import cats.Monad
import cats.implicits._

import entities.domain.slack.{URLData, URLName}
import entities.repositories.slack.SlackRepository
import usecases.UseCase

case class SlackGetURLInputData(urlName: URLName)
case class SlackGetURLOutputData(urlData: Option[URLData])

class SlackGetURLUseCase[F[_]](slackRepository: SlackRepository[F])
  extends UseCase[F, SlackGetURLInputData, SlackGetURLOutputData] {

  def execute(inputData: SlackGetURLInputData)(implicit F: Monad[F]): F[SlackGetURLOutputData] =
    for {
      urlData <- slackRepository.findURL( inputData.urlName )
    } yield SlackGetURLOutputData( urlData )

}