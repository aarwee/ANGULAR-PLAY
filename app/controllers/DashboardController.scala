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

  val addForm= Form(
    tuple(

      "name"-> nonEmptyText,
      "email" -> nonEmptyText,
      "address" -> nonEmptyText,
      "mobile" -> nonEmptyText,
      "emergency" -> nonEmptyText
    )
  )

  val updateForm= Form(
    tuple(

      "id"->nonEmptyText,
      "name"-> nonEmptyText,
      "email" -> nonEmptyText,
      "address" -> nonEmptyText,
      "mobile" -> nonEmptyText,
      "emergency" -> nonEmptyText
    )
  )

  val deleteForm= Form(

    "id"-> number

  )



  implicit val residentWrites: Writes[User] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "email").write[String] and
      (JsPath \ "address").write[String] and
      (JsPath \ "mobile").write[String] and
      (JsPath \ "emergency").write[String] and
      (JsPath \ "id").write[Int]
    )(unlift(User.unapply))




  def showUser = Action{
    userRepo.create()
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
    addForm.bindFromRequest().fold(

      badForm => { println(badForm);BadRequest("BadForm")},
      data => {userRepo.add(data._1,data._2,data._3,data._4,data._5 ); Ok("Added")}
    )


  }

  def update(id:String) = Action.async{ implicit request =>

    val data = userRepo.getUserById(Integer.parseInt(id))

   data.map { values =>  Ok(views.html.update(values.get))  }

  }






  def deleteData=Action{ implicit request =>

   deleteForm.bindFromRequest.fold(

     badForm => BadRequest("BAdForm"),
     data => { userRepo.deleteUser(data)
     Ok("DELETED")
     }
   )
  }

  def updateData = Action{ implicit request =>

    updateForm.bindFromRequest().fold(

      badForm => {BadRequest("BAd Form")},

      data => {
        userRepo.updateUser(Integer.parseInt(data._1), data._2, data._3, data._4, data._5, data._6); Ok("updated")}
    )

  }

}
