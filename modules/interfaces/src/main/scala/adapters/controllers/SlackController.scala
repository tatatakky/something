package adapters.controllers

import adapters.DIModules
import cats.effect.IO
import entities.domain.slack.URLName
import usecases.slack.{SlackGetURLInputData, SlackGetURLUseCase}
import wvlet.airframe.Design

class SlackController {

  val design: Design = DIModules.designs
  val session = design.newSession
  session.start

  def getURL(urlName: String): String = {
    val inputData: SlackGetURLInputData = SlackGetURLInputData( URLName(urlName) )
    val slackGetURLUseCase = session.build[SlackGetURLUseCase[IO]]
    slackGetURLUseCase.execute(inputData).unsafeRunSync()(cats.effect.unsafe.implicits.global).toString
  }
}