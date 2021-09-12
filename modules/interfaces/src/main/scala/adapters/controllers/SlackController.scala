package adapters.controllers

import entities.domain.slack.URLName

import usecases.slack.{SlackGetURLInputData, SlackGetURLUseCase}

import adapters.DIModules
import adapters.presenters.SlackPresenter

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import wvlet.airframe.{Design, Session}

class SlackController {

  implicit val runtime: IORuntime = cats.effect.unsafe.implicits.global

  val design: Design   = DIModules.designs
  val session: Session = design.newSession
  session.start

  def getURL(urlName: String): String = {
    val inputData: SlackGetURLInputData = SlackGetURLInputData( URLName(urlName) )
    val slackGetURLUseCase = session.build[SlackGetURLUseCase[IO]]
    SlackPresenter.convert( slackGetURLUseCase.execute(inputData).unsafeRunSync() )
  }
}