package usecases.slack.interactor

import cats.Monad
import cats.implicits._

import entities.domain.slack.{URLData, URLName}
import entities.repositories.slack.SlackRepository
import usecases.slack.inputboundary.UseCase
import usecases.slack.outputboundary.SlackGetURLPresenter

case class SlackGetURLInputData(urlName: URLName)
case class SlackGetURLOutputData(urlData: Option[URLData])

class SlackGetURLUseCase[F[_]](implicit slackRepository: SlackRepository[F], slackGetURLPresenter: SlackGetURLPresenter[F])
  extends UseCase[F, SlackGetURLInputData, SlackGetURLOutputData] {

  def execute(inputData: SlackGetURLInputData)(implicit F: Monad[F]): F[SlackGetURLOutputData] =
    for {
      urlData    <- slackRepository.findURL( inputData.urlName )
      outputData <- slackGetURLPresenter.convert(urlData)
    } yield outputData

}