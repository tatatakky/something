package slack

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.response.chat.ChatPostMessageResponse

import adapters.controllers.SlackController

trait SlackBot extends SlackEnv {
  val slack: Slack = Slack.getInstance
  val methods: MethodsClient = slack.methods(token)

  def sendURLBy(urlName: String): ChatPostMessageResponse = {
    val text = (new SlackController).getURL(urlName)
    val request: ChatPostMessageRequest = ChatPostMessageRequest.builder()
      .channel(channel_name) // Use a channel ID `C1234567` is preferrable
      //.text(":wave: Hello !!")
      .text(text)
      .build()
    methods.chatPostMessage(request)
  }
}
object SlackBot extends SlackBot