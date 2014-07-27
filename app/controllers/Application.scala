package controllers

import model.Message
import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Concurrent
object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
  
  val (out, channel) = Concurrent.broadcast[JsValue]
  
  def chat = WebSocket.using[JsValue] { request =>
    
    var user: Option[String] = None
    val in = Iteratee.foreach[JsValue] { data =>
      user map { username =>
        (data \ "message").asOpt[String] foreach { message =>
          channel.push(Message(username, message).toJson)
        }
      } getOrElse {
        (data \ "username").asOpt[String] foreach { username =>
          user = Some(username)
          channel.push(Json.obj("message" -> s"$username has connected."))
        }
      }
    } map { _ =>
      user foreach { username =>
        channel.push(Json.obj("message" -> s"$username has disconnected."))
      }
    }
    
    (in, out)
  }

}