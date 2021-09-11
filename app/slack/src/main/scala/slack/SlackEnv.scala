package slack

trait SlackEnv {
  lazy val token: String = System.getenv("SLACK_TOKEN")
  lazy val channel_name: String = System.getenv("SLACK_CHANNEL")
}