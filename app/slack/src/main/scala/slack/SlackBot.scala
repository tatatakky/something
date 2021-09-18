package slack

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.response.chat.ChatPostMessageResponse

import entities.domain.slack.URLData
import usecases.slack.interactor.SlackGetURLOutputData
import adapters.controllers.SlackController

trait SlackBot extends SlackEnv {
  val slack: Slack = Slack.getInstance
  val methods: MethodsClient = slack.methods(token)

  def sendURLBy(urlName: String): ChatPostMessageResponse = {
    val outputData: SlackGetURLOutputData = (new SlackController).getURL(urlName)
    val text = showOnSlack(outputData)
    val request: ChatPostMessageRequest = ChatPostMessageRequest.builder()
      .channel(channel_name)
      .text(text)
      .build()
    methods.chatPostMessage(request)
  }

  private def display(urlData: URLData): String =
    s"URL: ${urlData.url.value}\n作者: ${urlData.org.name}"

  private def showOnSlack[UseCaseOutput <: SlackGetURLOutputData](useCaseOutput: UseCaseOutput): String =
    useCaseOutput match {
      case SlackGetURLOutputData(optUrlData) =>
        optUrlData.map(display).getOrElse("The document URL Not Found...")
      case _ =>
        "No implemented..."
    }

}
object SlackBot extends SlackBot