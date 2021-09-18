package usecases.slack.outputboundary

import entities.domain.slack.URLData
import usecases.slack.interactor.SlackGetURLOutputData

trait SlackGetURLPresenter[F[_]] {
  def convert(urlData: Option[URLData]): F[SlackGetURLOutputData]
}