package adapters.controllers

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import wvlet.airframe.{Design, Session}

import entities.domain.slack.URLName
import usecases.slack.interactor.{SlackGetURLInputData, SlackGetURLOutputData, SlackGetURLUseCase}
import adapters.DIModules

class SlackController {

  implicit val runtime: IORuntime = cats.effect.unsafe.implicits.global

  val design: Design   = DIModules.designs
  val session: Session = design.newSession
  session.start

  def getURL(urlName: String): SlackGetURLOutputData = {
    val inputData: SlackGetURLInputData = SlackGetURLInputData( URLName(urlName) )
    val slackGetURLUseCase = session.build[SlackGetURLUseCase[IO]]
    slackGetURLUseCase.execute(inputData).unsafeRunSync()
  }
}