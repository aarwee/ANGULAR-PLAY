package controllers

import com.google.inject.Inject
import models.{User, UserRepo}
import javax.inject._
import play.api._
import play.api.libs.json.{Writes, JsPath, Reads, Json}
import play.api.mvc.Action
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
/**
  * Created by knoldus on 14/3/16.
  */
class DashboardController @Inject()(userRepo: UserRepo) extends Controller{



  implicit val residentWrites: Writes[User] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "email").write[String] and
      (JsPath \ "password").write[String] and
      (JsPath \ "mobile").write[String] and
      (JsPath \ "admin").write[Boolean] and
      (JsPath \ "id").write[Int]
    )(unlift(User.unapply))

  def showUser = Action{
    Ok(views.html.user())
  }

  def show = Action.async { implicit request =>
  val x = userRepo.getAll


   x.map{ a=> { val res = Json.toJson(a);
     Ok(res)
   }
   }
  }


  def add = Action.async { implicit request =>
    val x = userRepo.getAll


    x.map{ a=> { val res = Json.toJson(a);
      Ok(res)
    }
    }
  }


}
