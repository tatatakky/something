package adapters

import cats.Monad
import cats.effect.IO
import wvlet.airframe.{Design, newDesign}

import entities.repositories.slack.SlackRepository
import usecases.slack.outputboundary.SlackGetURLPresenter
import interfaces.repositories.slack.SlackRepositoryImpl
import adapters.presenters.SlackGetURLPresenterImpl
import support.IOMonad

trait DIModules {

  private[adapters] def designOfRepositories: Design =
    newDesign
      .bind[SlackRepository[IO]].to[SlackRepositoryImpl]

  private[adapters] def designOfPresenters: Design =
    newDesign
      .bind[SlackGetURLPresenter[IO]].to[SlackGetURLPresenterImpl]

  def designs: Design =
    newDesign
      .add(designOfRepositories)
      .add(designOfPresenters)
      .bind[Monad[IO]].to[IOMonad]
}
object DIModules extends DIModules
