package adapters.presenters

import entities.domain.slack.URLData
import usecases.slack.SlackGetURLOutputData

trait SlackPresenter {

  private def display(urlData: URLData): String =
    s"URL: ${urlData.url.value}\n作者: ${urlData.org.name}"

  def convert[UseCaseOutput <: SlackGetURLOutputData](useCaseOutput: UseCaseOutput): String =
    useCaseOutput match {
      case SlackGetURLOutputData(optUrlData) =>
        optUrlData.map(display).getOrElse("The document URL Not Found...")
      case _ =>
        "No implemented..."
    }
}
object SlackPresenter extends SlackPresenter