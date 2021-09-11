package adapters

import cats.Monad
import cats.effect.IO
import wvlet.airframe.{Design, newDesign}

import entities.repositories.slack.SlackRepository
import interfaces.repositories.slack.SlackRepositoryImpl
import support.IOMonad

trait DIModules {
  def designs: Design =
    newDesign
      .bind[SlackRepository[IO]].to[SlackRepositoryImpl]
      .bind[Monad[IO]].to[IOMonad]
}
object DIModules extends DIModules
