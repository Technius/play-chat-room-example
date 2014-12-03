package models

import play.api.mvc.WebSocket.FrameFormatter
import play.api.libs.json.Json
import play.api.libs.json.Writes

case class Message(username: String, value: String) {
  def toJson = Json.toJson(this)(Message.writes)
}

object Message {
  val writes = new Writes[Message] {
    def writes(message: Message) = Json.obj (
      "username" -> message.username,
      "message" -> message.value
    )
  }
}
