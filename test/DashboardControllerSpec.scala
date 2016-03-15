import controllers.DashboardController
import models.{User, UserRepo}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

/**
  * Created by knoldus on 15/3/16.
  */


class DashboardControllerSpec extends PlaySpecification with Mockito {

  "dashboard Controller " should {

    val service = mock[UserRepo]
    val controller = new DashboardController(service)


    "show" in new WithApplication() {
      when(service.getAll).thenReturn(Future(List(User("deepti", "deepti@gmail.com", "Delhi", "9650291122", "9876543210", 1))))
      val res = call(controller.show, FakeRequest(GET, "/show"))
      status(res) must equalTo(200)

    }


    "showUser" in new WithApplication() {
      when(service.create).thenReturn(true)
      val res = call(controller.showUser, FakeRequest(GET, "/showUser"))
      status(res) must equalTo(200)

    }

    "add" in new WithApplication() {
      val res = call(controller.add, FakeRequest(GET, "/add"))
      status(res) must equalTo(200)

    }

    "add" in new WithApplication() {
      val res = call(controller.add, FakeRequest(GET, "/add"))
      status(res) must equalTo(200)

    }

    "add Data in correct format" in new WithApplication() {
      when(service.add("rishabh", "rishabh@gmail.com", "Kanpur", "9876543210", "1234567890")).thenReturn(Future(1))
      val res = call(controller.addData, FakeRequest(POST, "/addData").withFormUrlEncodedBody("name" -> "rishabh", "email" -> "rishabh@gmail.com", "address" -> "Kanpur", "mobile" -> "9876543210", "emergency" -> "1234567890").withSession("id" -> "2"))
      status(res) must equalTo(200)


    }
    "add Data in incorrect format" in new WithApplication() {
      when(service.add("rishabh", "rishabh@gmail.com", "Kanpur", "9876543210", "1234567890")).thenReturn(Future(1))
      val res = call(controller.addData, FakeRequest(POST, "/addData").withFormUrlEncodedBody( "email" -> "rishabh@gmail.com", "address" -> "Kanpur", "mobile" -> "9876543210", "emergency" -> "1234567890").withSession("id" -> "2"))
      status(res) must equalTo(400)


    }

    "delete Data in correct format" in new WithApplication() {
      when(service.deleteUser(1)).thenReturn(Future(1))
      val res = call(controller.addData, FakeRequest(POST, "/addData").withFormUrlEncodedBody("name" -> "rishabh", "email" -> "rishabh@gmail.com", "address" -> "Kanpur", "mobile" -> "9876543210", "emergency" -> "1234567890").withSession("id" -> "2"))
      status(res) must equalTo(200)


    }
    "delete Data in incorrect format" in new WithApplication() {
      when(service.deleteUser(1)).thenReturn(Future(1))
      val res = call(controller.addData, FakeRequest(POST, "/addData").withFormUrlEncodedBody( "email" -> "rishabh@gmail.com", "address" -> "Kanpur", "mobile" -> "9876543210", "emergency" -> "1234567890").withSession("id" -> "2"))
      status(res) must equalTo(400)


    }


    "update Data in wrong format" in new WithApplication() {
      when(service.updateUser(1, "kunal", "kunal@gmail.com", "Faridabad", "9876543210", "1234567890")).thenReturn(Future(1))
      val res = call(controller.updateData, FakeRequest(POST, "/updateData").withFormUrlEncodedBody("name" -> "kunal", "email" -> "kunal@gmail.com", "address" -> "Faridabad", "mobile" -> "9876543210", "emergency" -> "1234567890").withSession("id" -> "1"))
      status(res) must equalTo(400)

    }

    "update Data in correct format" in new WithApplication() {
      when(service.updateUser(1, "kunal", "kunal@gmail.com", "Faridabad", "9876543210", "1234567890")).thenReturn(Future(1))
      val res = call(controller.updateData, FakeRequest(POST, "/updateData").withFormUrlEncodedBody("id"->"1","name"->"kunal","email"-> "kunal@gmail.com","address"-> "Faridabad","mobile"-> "9876543210","emergency"-> "1234567890").withSession("id" -> "1"))
      status(res) must equalTo(200)

    }

    "update " in new WithApplication() {
      when(service.getUserById(1)).thenReturn(Future(Option(User("deepti","deepu@gmail.com","DElhi","1234567890","9876543210",1))))
      val res = call(controller.update("1"), FakeRequest(GET, "/update/1"))
      status(res) must equalTo(200)

    }
  }
}
