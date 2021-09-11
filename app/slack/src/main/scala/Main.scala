object Main {
  import slack.SlackBot
  def main(args: Array[String]): Unit = {
    SlackBot.sendURLBy("scala-lang")
  }
}
