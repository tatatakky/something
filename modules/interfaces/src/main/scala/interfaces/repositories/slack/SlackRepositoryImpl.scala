package interfaces.repositories.slack

import cats.effect.IO
import entities.domain.slack.{Organization, URL, URLData, URLName}
import entities.repositories.slack.SlackRepository

class SlackRepositoryImpl extends SlackRepository[IO] {
  def findURL(urlName: URLName): IO[Option[URLData]] =
    IO{ Some(URLData( URL("https://www.scala-lang.org/"), Organization("EPFL") )) }
}
