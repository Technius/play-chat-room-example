package controllers

import model.Message
import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc._
import play.api.mvc.WebSocket.FrameFormatter
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Concurrent
object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
  
  implicit val format = Json.format[Message]
  implicit val frameFormat = FrameFormatter.jsonFrame[Message]
  
  val (out, channel) = Concurrent.broadcast[Message]
  
  def chat = WebSocket.using[Message] { request =>
    
    val in = Iteratee.foreach[Message] { msg =>
      channel.push(msg)
    }
    
    (in, out)
  }

}