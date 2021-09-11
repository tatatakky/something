package entities.repositories.slack

import entities.domain.slack.{ URLData, URLName }

trait SlackRepository[F[_]] {
  def findURL(urlName: URLName): F[Option[URLData]]
}