name := "something"

version := "0.1"

val baseName = "something"

val scala2_13 = "2.13.6"

// slack-api-client
lazy val slackVersion = "1.12.1"
// cats
lazy val catsVersion = "2.6.1"
// cats-effect
lazy val catsEffectVersion = "3.2.8"
// airframe
lazy val airframeVersion = "21.9.0"
// scala-test
lazy val scalatestVersion = "3.2.9"

// modules for entities
lazy val `entities` = (project in file("modules/entities")).settings(
  name := s"$baseName-entities",
  scalaVersion := scala2_13
)

// modules for useCases
lazy val `usecases` = (project in file("modules/usecases"))
  .settings(
    name := s"$baseName-usecases",
    scalaVersion := scala2_13,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % catsVersion,
      "org.scalatest" %% "scalatest" % scalatestVersion
    )
  )
  .dependsOn(entities)

// modules for interfaces
lazy val `interfaces` = (project in file("modules/interfaces"))
  .settings(
    name := s"$baseName-interfaces",
    scalaVersion := scala2_13,
    libraryDependencies ++= Seq(
      "org.typelevel"      %% "cats-effect" % catsEffectVersion,
      "org.wvlet.airframe" %% "airframe"    % airframeVersion
    )
  )
  .dependsOn(usecases, entities, support)

// modules for interfaces
lazy val `support` = (project in file("support"))
  .settings(
    name := s"$baseName-util",
    scalaVersion := scala2_13,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"   % catsVersion,
      "org.typelevel" %% "cats-effect" % catsEffectVersion
    )
  )

lazy val `app` = (project in file("app/slack"))
  .settings(
    name := s"$baseName-slack-app",
    scalaVersion := scala2_13,
    libraryDependencies ++= Seq(
      "com.slack.api" % "slack-api-client" % slackVersion
    )
  )
  .dependsOn( `interfaces` )

lazy val root = (project in file(".")).settings(
  scalaVersion := "2.13.6",
)
  .dependsOn( `app` )
  .aggregate(`entities`, `usecases`, `interfaces`)
