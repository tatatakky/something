package adapters.presenters

import cats.effect.IO
import entities.domain.slack.URLData
import usecases.slack.interactor.SlackGetURLOutputData
import usecases.slack.outputboundary.SlackGetURLPresenter

class SlackGetURLPresenterImpl extends SlackGetURLPresenter[IO] {
  def convert(maybeUrlData: Option[URLData]): IO[SlackGetURLOutputData] =
    maybeUrlData match {
      case Some(urlData) => IO{ SlackGetURLOutputData(Some(urlData)) }
      case None          => IO{ SlackGetURLOutputData(None) }
    }
}
