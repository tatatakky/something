package usecases.slack.interactor

import cats.Id
import entities.domain.slack.{Organization, URL, URLData, URLName}
import entities.repositories.slack.SlackRepository
import org.scalatest.flatspec.AnyFlatSpec
import usecases.slack.outputboundary.SlackGetURLPresenter

class SlackGetURLUseCaseSpec extends AnyFlatSpec {

  implicit val slackRepository: SlackRepository[Id] = new SlackRepository[Id] {
    override def findURL(urlName: URLName): Id[Option[URLData]] =
      urlName match {
        case URLName("scala-lang") => Some(URLData( URL("https://www.scala-lang.org/"), Organization("EPFL") ))
        case _                     => None
      }
  }

  implicit val slackGetURLPresenter: SlackGetURLPresenter[Id] = new SlackGetURLPresenter[Id] {
    def convert(urlData: Option[URLData]): Id[SlackGetURLOutputData] =
      urlData match {
        case Some(urlData) => SlackGetURLOutputData(Some(urlData))
        case None          => SlackGetURLOutputData(None)
      }
  }

  val slackGetURLUseCase: SlackGetURLUseCase[Id] = new SlackGetURLUseCase[Id]

  "SlackGetURLUseCase" should "send URL of scala-lang which is Some." in {
    val inputData_scala_lang: SlackGetURLInputData = SlackGetURLInputData( URLName("scala-lang") )
    val res_some      = slackGetURLUseCase.execute(inputData_scala_lang)
    val expected_some = SlackGetURLOutputData( Some(URLData( URL("https://www.scala-lang.org/"), Organization("EPFL") )) )
    assert( res_some == expected_some )
  }

  it should "not send URL of rust-lang which is None." in {
    val inputData_rust_lang: SlackGetURLInputData = SlackGetURLInputData( URLName("rust-lang") )
    val res_none      = slackGetURLUseCase.execute(inputData_rust_lang)
    val expected_none = SlackGetURLOutputData( None )
    assert( res_none == expected_none )
  }

}