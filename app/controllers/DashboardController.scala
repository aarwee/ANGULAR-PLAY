package controllers

import com.google.inject.Inject
import models.{User, UserRepo}
import javax.inject._
import play.api._

import play.api.libs.json.{Writes, JsPath, Reads, Json}
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
/**
  * Created by knoldus on 14/3/16.
  */
class DashboardController @Inject()(userRepo: UserRepo) extends Controller{

  val awardForm= Form(
    tuple(

      "name"-> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "mobile" -> nonEmptyText,
      "admin" -> nonEmptyText
    )
  )

  val updateForm= Form(
    tuple(

      "old-name"->nonEmptyText,
      "name"-> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "mobile" -> nonEmptyText,
      "admin" -> nonEmptyText
    )
  )

  val deleteForm= Form(

    "id"-> number

  )



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




  def add = Action{
    Ok(views.html.add())
  }
  def addData =Action {implicit request =>
    awardForm.bindFromRequest().fold(

      badForm => {Ok("hey")},
      data => {userRepo.add(data._1,data._2,data._3,data._4,data._5 ); Ok("deleted")}
    )


  }
/*
  def update= Action{ implicit request =>
    Ok(views.html.update())

  }*/

def delete = Action{ implicit request =>
  Ok(views.html.delete())

}


  def deleteData=Action{ implicit request =>

   deleteForm.bindFromRequest.fold(

     badForm => Ok(""),
     data => { userRepo.deleteUser(data); Ok("deleted")}


   )


  }

 /* def updateData = Action{ implicit request =>

    updateForm.bindFromRequest().fold(

      badForm => {println(badForm);
        Ok("hey")},

      data => {
        userRepo.updateUser(data._1, data._2, data._3, data._4, data._5, data._6); Ok("")}
    )

  }*/

}
